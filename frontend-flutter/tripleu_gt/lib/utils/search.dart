import 'package:flutter/material.dart';
import 'package:tripleu_gt/model/users_search.dart';
import 'package:tripleu_gt/services/user_service.dart';

class Search extends SearchDelegate {
  UserService service = new UserService();
  List<Datum> _data;
  @override
  List<Widget> buildActions(BuildContext context) {
    return <Widget>[
      IconButton(
          icon: Icon(Icons.close),
          onPressed: () {
            query = "";
          })
    ];
  }

  @override
  Widget buildLeading(BuildContext context) {
    return IconButton(
      icon: Icon(Icons.arrow_back),
      onPressed: () {
        close(context, null);
      },
    );
  }

  String selectedResult;
  @override
  Widget buildResults(BuildContext context) {
    return Container(
        child: Center(
      child: Text(selectedResult),
    ));
  }

  final List<String> listExample;
  Search(this.listExample);
  List<String> recentList = ["Text 4", "Text 3"];
  @override
  Widget buildSuggestions(BuildContext context) {
    print(query);
    _data = [];

    return FutureBuilder(
      future: service.searchUserByMail(query),
      builder: (context, snapshot) {
        if (!snapshot.hasData) {
          return Center(
            child: CircularProgressIndicator(),
          );
        } else {
          _data = [];
          _data = snapshot.data ?? [];
          return ListView.builder(
            itemCount: _data.length,
            scrollDirection: Axis.vertical,
            shrinkWrap: true,
            itemBuilder: (BuildContext context, int index) {
              return _card(_data[index]);
            },
          );
        }
      },
    );
  }

  Widget _card(dynamic item) {
    return Card(
      elevation: 8.0,
      margin: new EdgeInsets.symmetric(horizontal: 10.0, vertical: 8.0),
      child: Container(
        decoration: BoxDecoration(color: Colors.white),
        child: _listTile(item),
      ),
    );
  }

  Widget _listTile(dynamic item) {
    return ListTile(
        contentPadding: EdgeInsets.symmetric(horizontal: 20.0, vertical: 10.0),
        leading: CircleAvatar(
          backgroundImage:
              NetworkImage((item.userImage != null) ? item.userImage : "https://www.pinclipart.com/picdir/middle/157-1578186_user-profile-default-image-png-clipart.png"),  // no matter how big it is, it won't overflow
        ),
        title: Text(
          item.userMail,
          style: TextStyle(color: Colors.black87, fontWeight: FontWeight.bold),
        ),
        // subtitle: Text("Intermediate", style: TextStyle(color: Colors.white)),
        trailing: Icon(Icons.keyboard_arrow_right,
            color: Colors.black87, size: 30.0));
  }
}
