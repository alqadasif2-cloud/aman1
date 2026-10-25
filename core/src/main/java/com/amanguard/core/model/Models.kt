package com.amanguard.core.model

import androidx.annotation.StringRes
import com.amanguard.core.R

enum class PermState { GRANTED, DENIED, NOT_REQUESTED, NEEDS_MANUAL, PARTIAL, UNAVAILABLE, REVOKED, NEEDS_REAUTH }

/** Permissions/settings actually used by features implemented in this version. */
enum class PermId(
    val key: String,
    @StringRes val title: Int,
    @StringRes val feature: Int,
    @StringRes val reason: Int,
) {
    LOCATION_FINE("location_fine", R.string.perm_location_fine_title, R.string.perm_location_fine_feature, R.string.perm_location_fine_reason),
    LOCATION_BACKGROUND("location_background", R.string.perm_location_bg_title, R.string.perm_location_bg_feature, R.string.perm_location_bg_reason),
    LOCATION_SERVICES("location_services", R.string.perm_location_services_title, R.string.perm_location_services_feature, R.string.perm_location_services_reason),
    NOTIFICATIONS("notifications", R.string.perm_notifications_title, R.string.perm_notifications_feature, R.string.perm_notifications_reason),
    USAGE_ACCESS("usage_access", R.string.perm_usage_title, R.string.perm_usage_feature, R.string.perm_usage_reason),
    BATTERY_OPT("battery_opt", R.string.perm_battery_title, R.string.perm_battery_feature, R.string.perm_battery_reason),
}

data class PermInfo(val id: PermId, val state: PermState, val checkedAt: Long)

/** Command types implemented end-to-end. Keep in sync with functions/src/index.ts */
object CommandTypes {
    const val LOCATION_REFRESH = "location.refresh"
    const val PERMISSIONS_CHECK = "permissions.check"
    const val SYNC_NOW = "sync.now"
    const val RULES_SYNC = "rules.sync"
}

enum class Connection { ONLINE, STALE, OFFLINE, UNKNOWN }
