import 'package:flutter/material.dart';

Color COLOR_DEFAULT_BD = Color(0XFFffffff);
Color COLOR_DEFAULT_PRIMARY = Color(0xFF1a237e);
Color COLOR_DEFAULT_PRIMARY_LIGHT = Color(0xFF534bae);
Color COLOR_DEFAULT_PRIMARY_DARK = Color(0xFF000051);
Color COLOR_DEFAULT_SECONDARY = Color(0xFF536dfe);
Color COLOR_DEFAULT_SECONDARY_LIGHT = Color(0xFF8f9bff);
Color COLOR_DEFAULT_SECONDARY_DARK = Color(0xFF0043ca);
Color COLOR_DEFAULT_TEXT_PRIMARY = Color(0xFFffffff);
Color COLOR_DEFAULT_TEXT_SECONDARY = Color(0xFF000000);

// Form(
//                         key: _formKey,
//                         child: ListView(
//                           children: <Widget>[
//                             TextFormField(
//                               keyboardType: TextInputType.number,
//                               decoration: const InputDecoration(
//                                 icon: const Icon(Icons.person),
//                                 hintText: 'Valor de X do ponto próximo',
//                                 labelText: 'Ponto Próximo P(x,y)',
//                               ),
//                               onSaved: (val){
//                                 _x = double.parse(val);
//                               },
//                             ),
//                            TextFormField(
//                               keyboardType: TextInputType.number,
//                               decoration: const InputDecoration(
//                                 icon: const Icon(Icons.calendar_today),
//                                 hintText: 'Valor de Y do ponto próximo',
//                                 labelText: 'Ponto Próximo P(x,y)',
//                               ),
//                               onSaved: (val){
//                                 _y = double.parse(val);
//                               },
//                             ),
//                           ],
//                         ),
//                       ),