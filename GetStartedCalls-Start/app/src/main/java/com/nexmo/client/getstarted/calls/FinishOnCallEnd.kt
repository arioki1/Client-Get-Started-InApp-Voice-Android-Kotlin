package com.nexmo.client.getstarted.calls

import android.support.v7.app.AppCompatActivity

import com.nexmo.client.NexmoCallEventListener
import com.nexmo.client.NexmoCallMember
import com.nexmo.client.NexmoCallStatus
import com.nexmo.client.NexmoMediaActionState

import java.lang.ref.WeakReference

class FinishOnCallEnd(activity: AppCompatActivity) : NexmoCallEventListener {
    private val activityRef: WeakReference<AppCompatActivity> = WeakReference(activity)

    override fun onMemberStatusUpdated(nexmoCallStatus: NexmoCallStatus, nexmoCallMember: NexmoCallMember) {
        if (nexmoCallStatus == NexmoCallStatus.COMPLETED || nexmoCallStatus == NexmoCallStatus.CANCELED) {
            activityRef.get()?.finish()
        }
    }

    override fun onMuteChanged(nexmoMediaActionState: NexmoMediaActionState, nexmoCallMember: NexmoCallMember) {

    }

    override fun onEarmuffChanged(nexmoMediaActionState: NexmoMediaActionState, nexmoCallMember: NexmoCallMember) {

    }
}