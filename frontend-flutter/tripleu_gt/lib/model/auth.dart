
// To parse this JSON data, do
//
//     final oAuth = oAuthFromMap(jsonString);

import 'dart:convert';

class OAuth {
    OAuth({
        this.accessToken,
        this.tokenType,
        this.refreshToken,
        this.expiresIn,
        this.scope,
        this.id,
        this.jti,
    });

    String accessToken;
    String tokenType;
    String refreshToken;
    int expiresIn;
    String scope;
    int id;
    String jti;

    factory OAuth.fromJson(String str) => OAuth.fromMap(json.decode(str));

    String toJson() => json.encode(toMap());

    factory OAuth.fromMap(Map<String, dynamic> json) => OAuth(
        accessToken: json["access_token"] == null ? null : json["access_token"],
        tokenType: json["token_type"] == null ? null : json["token_type"],
        refreshToken: json["refresh_token"] == null ? null : json["refresh_token"],
        expiresIn: json["expires_in"] == null ? null : json["expires_in"],
        scope: json["scope"] == null ? null : json["scope"],
        id: json["id"] == null ? null : json["id"],
        jti: json["jti"] == null ? null : json["jti"],
    );

    Map<String, dynamic> toMap() => {
        "access_token": accessToken == null ? null : accessToken,
        "token_type": tokenType == null ? null : tokenType,
        "refresh_token": refreshToken == null ? null : refreshToken,
        "expires_in": expiresIn == null ? null : expiresIn,
        "scope": scope == null ? null : scope,
        "id": id == null ? null : id,
        "jti": jti == null ? null : jti,
    };
}
