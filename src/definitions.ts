export interface BoxpressdFirebaseAuthPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
