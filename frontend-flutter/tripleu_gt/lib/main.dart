import 'dart:io';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:tripleu_gt/screens/home_page.dart';
import 'package:tripleu_gt/screens/landing_page.dart';
import 'package:tripleu_gt/screens/profile_page.dart';
import 'package:tripleu_gt/screens/search_page.dart';

int initScreen;
Future<void> main() async {
  WidgetsFlutterBinding.ensureInitialized();
  SharedPreferences prefs = await SharedPreferences.getInstance();
  initScreen = await prefs.getInt("initScreen");
  await prefs.setInt("initScreen", 1);
  // await prefs.clear();
  print('initScreen $initScreen');
  runApp(MyApp());
}
 
class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    SystemChrome.setSystemUIOverlayStyle(SystemUiOverlayStyle(
      statusBarColor: Colors.transparent,
      statusBarIconBrightness: Brightness.dark,
      statusBarBrightness:
      Platform.isAndroid ? Brightness.dark : Brightness.light,
      systemNavigationBarColor: Colors.white,
      systemNavigationBarDividerColor: Colors.grey,
      systemNavigationBarIconBrightness: Brightness.dark,  
    ));
    return MaterialApp(
      title: '',
      debugShowCheckedModeBanner: false,
      theme: ThemeData(
        primarySwatch: Colors.blue,
        platform: TargetPlatform.iOS, 
      ),
      initialRoute: initScreen == 0 || initScreen == null ? "landing":"/",
      routes: {
        'landing': (BuildContext context) => LandingPage(),
        '/': (BuildContext context) => HomePage(),
        '/profile': (BuildContext context) => ProfilePage(),
        '/search': (BuildContext context) => SearchPage(),
               
      },
      
    );
  }
}