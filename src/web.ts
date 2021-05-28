import { WebPlugin } from '@capacitor/core';

import type { BoxpressdFirebaseAuthPlugin } from './definitions';

export class BoxpressdFirebaseAuthWeb
  extends WebPlugin
  implements BoxpressdFirebaseAuthPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
