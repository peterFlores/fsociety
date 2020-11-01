import 'dart:convert';

import 'package:tripleu_gt/model/auth.dart';
import 'package:http/http.dart' as http;

class AuthService {
  String _url = '3.22.230.92:9100';
  String _path = '/oauth/token';

  Future<OAuth> login(String username, String password) async {

    String user = 'socialapp';
    String pass = '12345';
    String basicAuth =
      'Basic ' + base64Encode(utf8.encode('$user:$pass'));

  final url = Uri.http(_url, _path);
    final resp = await http.post(url, body: {
      "username": username,
      "password": password,
      "grant_type": "password"
    }, headers: <String, String>{
      "Authorization": basicAuth
    });
    final decoded = json.decode(resp.body);
        //rprint(decoded);

    final  oauth = new OAuth.fromMap(decoded);
    print(oauth);
    return (resp.statusCode == 200 ) ? oauth : null;
  }
}