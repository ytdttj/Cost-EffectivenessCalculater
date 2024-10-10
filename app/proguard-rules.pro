# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
# 保留所有的活动类不被混淆
-keep public class * extends android.app.Activity

# 保留所有的Service类不被混淆
-keep public class * extends android.app.Service

# 保留所有的BroadcastReceiver类不被混淆
-keep public class * extends android.content.BroadcastReceiver

# 保留所有的ContentProvider类不被混淆
-keep public class * extends android.content.ContentProvider

# 保留带有指定注解的类、方法和字段不被混淆
-keep class androidx.annotation.Keep {
    *;
}
