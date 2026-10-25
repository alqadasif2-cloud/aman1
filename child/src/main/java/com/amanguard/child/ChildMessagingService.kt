package com.amanguard.child

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class ChildMessagingService : FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        saveToken(applicationContext, token)
    }
    override fun onMessageReceived(m: RemoteMessage) {
        if (m.data["kind"] != "command" || !ChildStore.isLinked(this)) return
        // Only accept commands addressed to THIS device of THIS family.
        if (m.data["familyId"] != ChildStore.familyId(this) || m.data["deviceId"] != ChildStore.deviceId(this)) return
        m.data["commandId"]?.let { Scheduler.runNow(this, it) }
    }
    companion object {
        fun saveToken(c: android.content.Context, token: String) {
            if (!ChildStore.isLinked(c)) return
            FirebaseFirestore.getInstance().document("families/${ChildStore.familyId(c)}/devices/${ChildStore.deviceId(c)}")
                .update("fcmToken", token)
        }
    }
}
