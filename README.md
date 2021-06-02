# Capacitor Firebase Auth Token Listener

** Note: iOS code coming soon. This plugin is currently for Android only. Pull requests welcome. **

The purpose of this library was to migrate from a fully Native app to one built with React using Capacitor. In order to prevent all users from being logged out after upgrading through the app store, this plugin makes use of Firebase's token ID listener to pass the token to the web layer.

## Install

```bash
npm install boxpressd/capacitor-firebase-token-listener
npx cap sync
```

## Usage

```typescript
import { Plugins } from '@capacitor/core';
const { FirebaseAuthTokenListener } = Plugins;
// [...]
this.firebaseTokenListener = await FirebaseAuthTokenListener.addListener(
    'firebaseTokenRefreshed',
    ({ tokenId }) => {
        console.debug('Token:', tokenId);
        // Do something with the token here, like pass it to your server
    },
);
// [...]
// To stop listening, use:
this.firebaseTokenListener.remove();
```

### Sign user out of the native layer

```typescript
FirebaseAuthTokenListener.signOut();
```

## Firebase

The new code set will need to be configured to use the same Firebase details as the original, native apps and you must be using the same package name and signing key (Android) for your project to be able to pull the auth token from the original sign in flow.

1. Follow instructions to add Firebase to your native project:
    * [Add Firebase to your Android project](https://firebase.google.com/docs/android/setup)
    * [Add Firebase to your iOS project ](https://firebase.google.com/docs/ios/setup)
2. If you haven't yet connected your app to your Firebase project, do so from the [Firebase console](https://console.firebase.google.com/).
    * Please, don't forgot your Google Service _.json_ or _.plist_ file or your app will crash on the startup.

#### Extra steps for iOS

1. Add a URL scheme to your project (iOS only)
    * See the official [documentation here](https://developers.google.com/identity/sign-in/ios/start-integrating#add_a_url_scheme_to_your_project).

2. Add the `Firebase/Auth` pod to the `Podfile` in `ios/App` like this:

```
target 'App' do
  capacitor_pods
  # Add your Pods here
  pod 'Firebase/Auth'
end
```

#### Extra steps for Android

1. Add Android SHA app hashes to Firebase console (android only)
    * For how to add the hash, check the [documentation here](https://support.google.com/firebase/answer/9137403?hl=en).
    * For _which_ hashes to add:
        * One set is from the local debug keystore which is added by Android Studio
        * Other two sets are from the "Google Play Console" if you go to "Signing" you can find your "upload" keys and your "release" keys
