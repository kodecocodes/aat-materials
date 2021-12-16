/*
 * Copyright (c) 2021 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * This project and source code may use libraries or frameworks that are
 * released under various Open-Source licenses. Use of those libraries and
 * frameworks are governed by their own individual licenses.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.raywenderlich.cinematic.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.raywenderlich.cinematic.R

val rubik = FontFamily(
  Font(R.font.rubik_regular, FontWeight.Normal),
  Font(R.font.rubik_bold, FontWeight.Bold),
  Font(R.font.rubik_one, FontWeight.ExtraBold)
)

val chivo = FontFamily(
  Font(R.font.chivo_regular, FontWeight.Normal),
  Font(R.font.chivo_bold, FontWeight.Bold)
)

val typography = Typography(


  h3 = TextStyle(
    fontSize = 36.sp,
    fontFamily = rubik,
    fontWeight = FontWeight.ExtraBold,
    letterSpacing = (-8).sp
  ),

  h4 = TextStyle(
    fontSize = 24.sp,
    fontFamily = rubik,
    fontWeight = FontWeight.ExtraBold,
    letterSpacing = (-8).sp
  ),

  h5 = TextStyle(
    fontSize = 24.sp,
    fontFamily = chivo,
    fontWeight = FontWeight.Bold
  ),

  h6 = TextStyle(
    fontSize = 18.sp,
    fontFamily = chivo,
    fontWeight = FontWeight.Bold
  ),

  subtitle1 = TextStyle(
    fontSize = 16.sp,
    fontFamily = chivo,
    fontWeight = FontWeight.Normal
  ),

  subtitle2 = TextStyle(
    fontSize = 16.sp,
    fontFamily = chivo,
    fontWeight = FontWeight.Bold
  ),

  body2 = TextStyle(
    fontSize = 14.sp,
    fontFamily = chivo,
    fontWeight = FontWeight.Normal,
    lineHeight = 22.sp
  ),

  button = TextStyle(
    fontSize = 14.sp,
    fontFamily = rubik,
    fontWeight = FontWeight.Normal
  ),

  overline = TextStyle(
    fontSize = 14.sp,
    fontFamily = rubik,
    fontWeight = FontWeight.Bold,
    textAlign = TextAlign.Center,
    letterSpacing = (3).sp
  )
)