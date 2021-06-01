import { WebPlugin } from '@capacitor/core';
import firebase from 'firebase/app';
import 'firebase/auth';

import type { BoxpressdFirebaseAuthPlugin } from './definitions';

export class BoxpressdFirebaseAuthWeb extends WebPlugin implements BoxpressdFirebaseAuthPlugin {
  async signOut(options: any): Promise<void> {
    console.log(options);
    return firebase.auth().signOut()
  }
}
