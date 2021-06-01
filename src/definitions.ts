declare module "@capacitor/core" {
  interface PluginRegistry {
    FirebaseAuthTokenListener?: BoxpressdFirebaseAuthPlugin;
  }
}

export interface BoxpressdFirebaseAuthPlugin {
  signOut(options: any): Promise<void>;
}
