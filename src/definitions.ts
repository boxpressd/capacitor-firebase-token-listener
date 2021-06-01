declare module "@capacitor/core" {
  interface PluginRegistry {
    BoxpressdFirebaseAuth?: BoxpressdFirebaseAuthPlugin;
  }
}

export interface BoxpressdFirebaseAuthPlugin {
  signOut(options: any): Promise<void>;
}
