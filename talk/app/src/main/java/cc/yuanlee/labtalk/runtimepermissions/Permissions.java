package cc.yuanlee.labtalk.runtimepermissions;

/**
 * Enum class to handle the different states
 * of permissions since the PackageManager only
 * has a granted and denied state.
 */
enum Permissions {
  GRANTED,
  DENIED,
  NOT_FOUND
}