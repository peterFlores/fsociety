import { Component, OnInit, ViewEncapsulation } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { ActivatedRoute } from "@angular/router";
import { NgbModal } from "@ng-bootstrap/ng-bootstrap";
import { User } from "app/models/user";
import { UserItem } from "app/models/user_item";
import { PostService } from "app/services/post.service";
import { UserService } from "app/services/user.service";
import { Subscription } from "rxjs";
import * as _ from 'lodash';


@Component({
  selector: "app-profile",
  templateUrl: "./profile.component.html",
  styleUrls: ["./profile.component.scss"],
  encapsulation: ViewEncapsulation.None,
})
export class ProfileComponent implements OnInit {
  sub: Subscription;
  user: UserItem;
  id: number = 0;
  files: File;
  imageError: string;
  isImageSaved: boolean;
  cardImageBase64: string;
  show = false;
  formulario: FormGroup;
  gender = [
    { value: "M", label: "MALE" },
    { value: "G", label: "FEMALE" },
  ];
  postForm: FormGroup;
  constructor(
    private formBuilder: FormBuilder,
    private _userService: UserService,
    private route: ActivatedRoute,
    private modalService: NgbModal,
    private _postService: PostService
  ) {
    
  }

  ngOnInit() {
    
    
    this.sub = this.route.params.subscribe((params) => {
      this.id = params.id || 0;
    });
    
    this._userService.getUserByID(2).subscribe((data) => {
      if (data.code == "1") {
        this.user = data.data;
        console.log(data);
      }
    });
    this.postForm = this.formBuilder.group({
      postContent: ['', [Validators.required,Validators.minLength(3)]],
      image: [''],
    });
  }
  upload() {
    console.log(this.files);
    if (this.files != null) {
      console.log(this.files);
      this._userService
        .updatePhoto(this.user.idUser, this.files)
        .subscribe((data) => {
          if (data.code == "1") {
            this.modalService.dismissAll();
            this.ngOnInit();
          }
        });
    } else {
      alert("no hay foto");
    }
  }
  onSubmit() {
    if (this.formulario.invalid) {
      return;
    }
    const name = this.formulario.get("name").value;
    const nickname = this.formulario.get("nickname").value;
    const gender = this.formulario.get("gender").value;
    const birthdate = this.formulario.get("birthdate").value;
    this._userService
      .updateUser(this.user.idUser, birthdate, name, nickname, gender)
      .subscribe((data) => {
        this.modalService.dismissAll();
        if (data.code === "1") {
            console.log(data);
          this.ngOnInit();
        }
      });
  }
  openUpload(picture) {
    if (this.id === 0) {
      this.modalService.open(picture);
    }
  }

  onPostSubmit() {
    if (this.postForm.invalid) {
      return;
    }
    console.log(this.postForm);
    const postContent = this.postForm.get('postContent').value;
    let image: string = this.postForm.get('image').value;
    image = image.replace("data:image/jpeg;base64,", "");
    console.log(image);
    this._postService.createPost(2, postContent, image).subscribe(data => {
      if (data.code === "1") {
        this.ngOnInit();
      }
    })
  }
  
  openLg(content) {
    this.formulario = this.formBuilder.group({
        name: [this.user.userName, [Validators.required]],
        nickname: [this.user.userNickname, [Validators.required]],
        birthdate: [this.user.userBirthDate, [Validators.required]],
        gender: [this.user.userGender, [Validators.required]],
      });
      console.log(this.formulario);
    this.modalService.open(content, { size: "lg" });
  }
  onSelect(files: FileList) {
    console.log(files);
    this.files = files[0];
  }
  onDelete(id:number) {
    console.log(id);

    this._postService.deletePost(id).subscribe((data) => {
      if (data.code === "1") {
        this.ngOnInit();
      }
    });
  }

  fileChangeEvent(fileInput: any) {
    this.imageError = null;
    if (fileInput.target.files && fileInput.target.files[0]) {
      // Size Filter Bytes
      const max_size = 20971520;
      const allowed_types = ['image/png', 'image/jpeg'];
      const max_height = 15200;
      const max_width = 25600;

      if (fileInput.target.files[0].size > max_size) {
        this.imageError =
          'Maximum size allowed is ' + max_size / 1000 + 'Mb';

        return false;
      }

      if (!_.includes(allowed_types, fileInput.target.files[0].type)) {
        this.imageError = 'Only Images are allowed ( JPG | PNG )';
        return false;
      }
      const reader = new FileReader();
      reader.onload = (e: any) => {
        const image = new Image();
        image.src = e.target.result;
        image.onload = rs => {
          const img_height = rs.currentTarget['height'];
          const img_width = rs.currentTarget['width'];

          console.log(img_height, img_width);


          if (img_height > max_height && img_width > max_width) {
            this.imageError =
              'Maximum dimentions allowed ' +
              max_height +
              '*' +
              max_width +
              'px';
            return false;
          } else {
            const imgBase64Path = e.target.result;
            this.cardImageBase64 = imgBase64Path;
            this.isImageSaved = true;
            this.postForm.get('image').setValue(this.cardImageBase64);
          }
        };
      };

      reader.readAsDataURL(fileInput.target.files[0]);
    }
  }

}
