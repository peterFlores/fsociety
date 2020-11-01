import 'dart:convert';

class UserS {
    UserS({
        this.code,
        this.message,
        this.data,
    });

    String code;
    String message;
    List<Datum> data;

    factory UserS.fromJson(String str) => UserS.fromMap(json.decode(str));

    String toJson() => json.encode(toMap());

    factory UserS.fromMap(Map<String, dynamic> json) => UserS(
        code: json["code"] == null ? null : json["code"],
        message: json["message"] == null ? null : json["message"],
        data: json["data"] == null ? null : List<Datum>.from(json["data"].map((x) => Datum.fromMap(x))),
    );

    Map<String, dynamic> toMap() => {
        "code": code == null ? null : code,
        "message": message == null ? null : message,
        "data": data == null ? null : List<dynamic>.from(data.map((x) => x.toMap())),
    };
}

class Datum {
    Datum({
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
    });

    int idUser;
    String userName;
    String userNickname;
    String userMail;
    dynamic userPassword;
    String userImage;
    String userBirthDate;
    String userGender;
    dynamic userRole;
    String userCreatedAt;
    String userStatus;

    factory Datum.fromJson(String str) => Datum.fromMap(json.decode(str));

    String toJson() => json.encode(toMap());

    factory Datum.fromMap(Map<String, dynamic> json) => Datum(
        idUser: json["idUser"] == null ? null : json["idUser"],
        userName: json["userName"] == null ? null : json["userName"],
        userNickname: json["userNickname"] == null ? null : json["userNickname"],
        userMail: json["userMail"] == null ? null : json["userMail"],
        userPassword: json["userPassword"],
        userImage: json["userImage"] == null ? null : json["userImage"],
        userBirthDate: json["userBirthDate"] == null ? null : json["userBirthDate"],
        userGender: json["userGender"] == null ? null : json["userGender"],
        userRole: json["userRole"],
        userCreatedAt: json["userCreatedAt"] == null ? null : json["userCreatedAt"],
        userStatus: json["userStatus"] == null ? null : json["userStatus"],
    );

    Map<String, dynamic> toMap() => {
        "idUser": idUser == null ? null : idUser,
        "userName": userName == null ? null : userName,
        "userNickname": userNickname == null ? null : userNickname,
        "userMail": userMail == null ? null : userMail,
        "userPassword": userPassword,
        "userImage": userImage == null ? null : userImage,
        "userBirthDate": userBirthDate == null ? null : userBirthDate,
        "userGender": userGender == null ? null : userGender,
        "userRole": userRole,
        "userCreatedAt": userCreatedAt == null ? null : userCreatedAt,
        "userStatus": userStatus == null ? null : userStatus,
    };
}
