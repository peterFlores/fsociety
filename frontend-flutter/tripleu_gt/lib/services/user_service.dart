
import 'dart:convert';


import 'package:http/http.dart' as http;
import 'package:tripleu_gt/model/users.dart';
import 'package:tripleu_gt/model/users_search.dart';


class UserService {

  //String _url = '10.0.3.2:89';
  String _url = '3.22.230.92:40000';
  String _path = '/';


  
  Future<Data> getUserById(int id) async {
    print("eds" +  id.toString());
    final url = Uri.http(_url, '/search_user/$id');
    final resp = await http.get(url);
    final decoded = json.decode(resp.body);
        //rprint(decoded);

    final  user = new User.fromMap(decoded);
    print(user.data.idUser);
    return user.data;
  }

  Future<List<Datum>> searchUserByMail(String search) async {
    final url = Uri.http(_url, '/search/byMail/$search');
    final resp = await http.get(url);
    final decoded = json.decode(resp.body);
        //rprint(decoded);

    final  user = new UserS.fromMap(decoded);
    print(user.data[0].userName);
    return user.data;
  }
  

}