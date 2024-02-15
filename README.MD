# Compose Multiplatform Application

## Before running!
 - check your system with [KDoctor](https://github.com/Kotlin/kdoctor)
 - install JDK 17 on your machine
 - add `local.properties` file to the project root and set a path to Android SDK there

### Android
To run the application on android device/emulator:  
 - open project in Android Studio and run imported android run configuration

To build the application bundle:
 - run `./gradlew :composeApp:assembleDebug`
 - find `.apk` file in `composeApp/build/outputs/apk/debug/composeApp-debug.apk`

### Desktop
Run the desktop application: `./gradlew :composeApp:run`

### iOS
To run the application on iPhone device/simulator:
 - Open `iosApp/iosApp.xcproject` in Xcode and run standard configuration
 - Or use [Kotlin Multiplatform Mobile plugin](https://plugins.jetbrains.com/plugin/14936-kotlin-multiplatform-mobile) for Android Studio

### Browser
Run the browser application: `./gradlew :composeApp:jsBrowserDevelopmentRun`

## DB設計


### エンティティと属性

1. **ユーザー(User)**
   - UserID (主キー)
   - ユーザー名
   - メールアドレス
   - パスワード (ハッシュ化)
   - その他ユーザー情報 (例: プロフィール情報など)

2. **ハッカソン(Hackathon)**
   - HackathonID (主キー)
   - ハッカソン名
   - 開催日
   - 終了日
   - 詳細説明

3. **チーム(Team)**
   - TeamID (主キー)
   - HackathonID (外部キー)
   - チーム名
   - 説明
   - 募集状況 (募集中、募集終了など)

4. **役割(Role)**
   - RoleID (主キー)
   - 役割名
   - 説明

### 関連テーブル

1. **チーム役割募集(TeamRole)**
   - TeamRoleID (主キー)
   - TeamID (外部キー)
   - RoleID (外部キー)
   - 必要人数
   - 現在の人数

2. **ユーザーチーム参加(UserTeam)**
   - UserTeamID (主キー)
   - UserID (外部キー)
   - TeamID (外部キー)
   - RoleID (外部キー)
   - 参加状況 (例: 確定、申請中など)
