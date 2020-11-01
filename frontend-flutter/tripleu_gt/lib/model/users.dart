import 'dart:convert';

class User {
    User({
        this.code,
        this.message,
        this.data,
    });

    String code;
    String message;
    Data data;

    factory User.fromJson(String str) => User.fromMap(json.decode(str));

    String toJson() => json.encode(toMap());

    factory User.fromMap(Map<String, dynamic> json) => User(
        code: json["code"] == null ? null : json["code"],
        message: json["message"] == null ? null : json["message"],
        data: json["data"] == null ? null : Data.fromMap(json["data"]),
    );

    Map<String, dynamic> toMap() => {
        "code": code == null ? null : code,
        "message": message == null ? null : message,
        "data": data == null ? null : data.toMap(),
    };
}

class Data {
    Data({
        this.idUser,
        this.userName,
        this.userNickname,
        this.userMail,
        this.userPassword,
        this.userImage,
        this.userBirthDate,
        this.userGender,
        this.userRole,
        this.userCreatedAt,
        this.userStatus,
        this.posts,
    });

    int idUser;
    String userName;
    String userNickname;
    String userMail;
    String userPassword;
    String userImage;
    String userBirthDate;
    String userGender;
    String userRole;
    String userCreatedAt;
    String userStatus;
    List<Post> posts;

    factory Data.fromJson(String str) => Data.fromMap(json.decode(str));

    String toJson() => json.encode(toMap());

    factory Data.fromMap(Map<String, dynamic> json) => Data(
        idUser: json["idUser"] == null ? null : json["idUser"],
        userName: json["userName"] == null ? null : json["userName"],
        userNickname: json["userNickname"] == null ? null : json["userNickname"],
        userMail: json["userMail"] == null ? null : json["userMail"],
        userPassword: json["userPassword"] == null ? null : json["userPassword"],
        userImage: json["userImage"] == null ? null : json["userImage"],
        userBirthDate: json["userBirthDate"] == null ? null : json["userBirthDate"],
        userGender: json["userGender"] == null ? null : json["userGender"],
        userRole: json["userRole"] == null ? null : json["userRole"],
        userCreatedAt: json["userCreatedAt"] == null ? null : json["userCreatedAt"],
        userStatus: json["userStatus"] == null ? null : json["userStatus"],
        posts: json["posts"] == null ? null : List<Post>.from(json["posts"].map((x) => Post.fromMap(x))),
    );

    Map<String, dynamic> toMap() => {
        "idUser": idUser == null ? null : idUser,
        "userName": userName == null ? null : userName,
        "userNickname": userNickname == null ? null : userNickname,
        "userMail": userMail == null ? null : userMail,
        "userPassword": userPassword == null ? null : userPassword,
        "userImage": userImage == null ? null : userImage,
        "userBirthDate": userBirthDate == null ? null : userBirthDate,
        "userGender": userGender == null ? null : userGender,
        "userRole": userRole == null ? null : userRole,
        "userCreatedAt": userCreatedAt == null ? null : userCreatedAt,
        "userStatus": userStatus == null ? null : userStatus,
        "posts": posts == null ? null : List<dynamic>.from(posts.map((x) => x.toMap())),
    };
}

class Post {
    Post({
        this.id,
        this.content,
        this.imagePath,
        this.createdAt,
        this.linkedPost,
        this.linkedPosts,
    });

    int id;
    String content;
    String imagePath;
    DateTime createdAt;
    List<dynamic> linkedPost;
    List<dynamic> linkedPosts;

    factory Post.fromJson(String str) => Post.fromMap(json.decode(str));

    String toJson() => json.encode(toMap());

    factory Post.fromMap(Map<String, dynamic> json) => Post(
        id: json["id"] == null ? null : json["id"],
        content: json["content"] == null ? null : json["content"],
        imagePath: json["imagePath"] == null ? null : json["imagePath"],
        createdAt: json["createdAt"] == null ? null : DateTime.parse(json["createdAt"]),
        linkedPost: json["linkedPost"] == null ? null : List<dynamic>.from(json["linkedPost"].map((x) => x)),
        linkedPosts: json["linkedPosts"] == null ? null : List<dynamic>.from(json["linkedPosts"].map((x) => x)),
    );

    Map<String, dynamic> toMap() => {
        "id": id == null ? null : id,
        "content": content == null ? null : content,
        "imagePath": imagePath == null ? null : imagePath,
        "createdAt": createdAt == null ? null : createdAt.toIso8601String(),
        "linkedPost": linkedPost == null ? null : List<dynamic>.from(linkedPost.map((x) => x)),
        "linkedPosts": linkedPosts == null ? null : List<dynamic>.from(linkedPosts.map((x) => x)),
    };
}