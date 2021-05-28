import { registerPlugin } from '@capacitor/core';

import type { BoxpressdFirebaseAuthPlugin } from './definitions';

const BoxpressdFirebaseAuth = registerPlugin<BoxpressdFirebaseAuthPlugin>(
  'BoxpressdFirebaseAuth',
  {
    web: () => import('./web').then(m => new m.BoxpressdFirebaseAuthWeb()),
  },
);

export * from './definitions';
export { BoxpressdFirebaseAuth };
