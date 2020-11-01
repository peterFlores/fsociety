import 'dart:async';
import 'dart:developer';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:tripleu_gt/constants/constants.dart';
import 'package:tripleu_gt/model/slider.dart';
import 'package:tripleu_gt/widgets/slide_dots.dart';
import 'package:tripleu_gt/widgets/slide_items/slide_item.dart';

class SliderLayoutView extends StatefulWidget {
  @override
  State<StatefulWidget> createState() => _SliderLayoutViewState();
}

class _SliderLayoutViewState extends State<SliderLayoutView> {
  int _currentPage = 0;
  final PageController _pageController = PageController(initialPage: 0);

  @override
  void initState() {
    super.initState();
    Timer.periodic(Duration(seconds: 5), (Timer timer) {
      if (_currentPage < 2) {
        _currentPage++;
      } else {
        _currentPage = 0;
      }
    });
  }

  @override
  void dispose() {
    super.dispose();
    _pageController.dispose();
  }

  _onPageChanged(int index) {
    setState(() {
      _currentPage = index;
    });
  }

  @override
  Widget build(BuildContext context) => topSliderLayout();

  Widget button(String text, String navigation, Alignment alignment) {
    return Align(
      alignment: alignment,
      child: Padding(
        padding: EdgeInsets.only(right: 15.0, bottom: 15.0),
        child: FlatButton(
          onPressed: () {

            if (navigation.isEmpty)
              _pageController.nextPage(
                  duration: Duration(milliseconds: 500), curve: Curves.easeIn);
            else
              Navigator.pushNamed(context, navigation);
          },
          child: Row(
            mainAxisAlignment: MainAxisAlignment.center,
            mainAxisSize: MainAxisSize.min,
            children: <Widget>[
              Text(
                text,
                style: TextStyle(
                  color: Color(0xff3f3d56),
                  fontFamily: Constants.OPEN_SANS,
                  fontWeight: FontWeight.w600,
                  fontSize: 16.0,
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }

  Widget topSliderLayout() => Container(
        child: Padding(
            padding: EdgeInsets.all(10.0),
            child: Stack(
              alignment: AlignmentDirectional.bottomCenter,
              children: <Widget>[
                PageView.builder(
                  scrollDirection: Axis.horizontal,
                  controller: _pageController,
                  onPageChanged: _onPageChanged,
                  itemCount: sliderArrayList.length,
                  itemBuilder: (ctx, i) => SlideItem(i),
                ),
                Stack(
                  alignment: AlignmentDirectional.topStart,
                  children: <Widget>[
                    _currentPage < 2
                        ? button(Constants.NEXT, "", Alignment.bottomRight)
                        : button(Constants.READY, "/", Alignment.bottomRight),
                    button(Constants.SKIP, "/", Alignment.bottomLeft),
                    Container(
                      alignment: AlignmentDirectional.bottomCenter,
                      margin: EdgeInsets.only(bottom: 30.0),
                      child: Row(
                        mainAxisAlignment: MainAxisAlignment.center,
                        children: <Widget>[
                          for (int i = 0; i < sliderArrayList.length; i++)
                            if (i == _currentPage)
                              SlideDots(true)
                            else
                              SlideDots(false)
                        ],
                      ),
                    )
                  ],
                ),
              ],
            )),
      );
}
