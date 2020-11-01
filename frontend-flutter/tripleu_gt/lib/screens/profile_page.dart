import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:tripleu_gt/constants/constants.dart';
import 'package:tripleu_gt/model/users.dart';
import 'package:tripleu_gt/services/user_service.dart';
import 'package:tripleu_gt/utils/search.dart';

class ProfilePage extends StatefulWidget {
  ProfilePage({Key key}) : super(key: key);

  @override
  _ProfilePageState createState() => _ProfilePageState();
}

class _ProfilePageState extends State<ProfilePage> {
  UserService service = new UserService();
  Data _data;
  final List<String> list = List.generate(10, (index) => "Text $index");
  int id;

  @override
  void initState() {
    super.initState();

    loadData().then((data) {
      setState(() {
        this.id = data; 
      });
    });
  }

  Future loadData() async {    
    final prefs = await SharedPreferences.getInstance();
    id = prefs.getInt("id") ?? 0;
    print(id);
    if (id == 0) {
      prefs.clear();
      Navigator.pushNamed(context, "/");
    }
    return id;    
  }
  @override
  Widget build(BuildContext context) {
    if (id == null) {
      loadData();
      return CircularProgressIndicator();
    } else {

    return _profilePage();
    }
  }
  getId() async {
    
  }
  Widget _profilePage() {
    return Scaffold(
      extendBodyBehindAppBar: true,
      resizeToAvoidBottomPadding: false,
      appBar: AppBar(
        backgroundColor: Colors.transparent,
        elevation: 0,
        actions: <Widget>[
          IconButton(
            icon: Icon(Icons.search),
            onPressed: () {
              showSearch(context: context, delegate: Search(list));
            }
          )
        ],
      ),
      body: _body(),
    );
  }

  Widget _body() {
    return Container(
      child: FutureBuilder(
        future: service.getUserById(id),
        builder: (context, snapshot) {
          if (!snapshot.hasData) {
            return CircularProgressIndicator();
          } else {
            _data = snapshot.data ?? [];
            print(_data.userBirthDate);
            return _profile(_data);
          }
        },
      ),
    );
  }

  Widget _profile(Data data) {
    final width = MediaQuery.of(context).size.width;
    final height = MediaQuery.of(context).size.height;

    return Scaffold(
      body: SingleChildScrollView(
        child: new Container(
        height: height,
        decoration: BoxDecoration(
          color: Colors.white,
        ),
        child: new Column(
          children: <Widget>[
            Container(
              height: height * 0.4,
              width: width * 1,
              decoration: BoxDecoration(
                  image: DecorationImage(
                      image: NetworkImage(_data.userImage), fit: BoxFit.cover)),
            ),
            info("NOMBRE", _data.userName),
            info("NICKNAME", _data.userNickname),
            info("EMAIL", _data.userMail),
            info("GENERO", _data.userGender),
            
          ],
        ),
      ),
      )
      
    );
  }

  Widget info(String tag, String data) {
    return Column(
      children: <Widget>[
        Divider(
          height: 24.0,
        ),
        new Row(
          children: <Widget>[
            new Expanded(
              child: new Padding(
                padding: const EdgeInsets.only(left: 40.0),
                child: new Text(
                  tag,
                  style: TextStyle(
                    fontFamily: Constants.OPEN_SANS,
                    fontWeight: FontWeight.w600,
                    color: Color(Constants.PRIMARY),
                    fontSize: 15.0,
                  ),
                ),
              ),
            ),
          ],
        ),
        new Container(
          width: MediaQuery.of(context).size.width,
          margin: const EdgeInsets.only(left: 40.0, right: 40.0, top: 10.0),
          alignment: Alignment.center,
          decoration: BoxDecoration(
            border: Border(
              bottom: BorderSide(
                  color: Color(Constants.PRIMARY),
                  width: 0.5,
                  style: BorderStyle.solid),
            ),
          ),
          padding: const EdgeInsets.only(left: 0.0, right: 10.0),
          child: new Row(
            crossAxisAlignment: CrossAxisAlignment.center,
            mainAxisAlignment: MainAxisAlignment.start,
            children: <Widget>[
              new Expanded(
                child: TextField(
                  enabled: false,
                  controller: TextEditingController()..text = data,
                  textAlign: TextAlign.left,
                  decoration: InputDecoration(
                    border: InputBorder.none,
                    hintStyle: TextStyle(
                        color: Colors.grey, fontFamily: Constants.POPPINS),
                  ),
                ),
              ),
            ],
          ),
        )
      ],
    );
  }
}
