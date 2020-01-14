package com.nexmo.client.getstarted.calls

import android.content.Context
import android.util.Log
import com.nexmo.client.NexmoCall
import com.nexmo.client.NexmoClient
import com.nexmo.client.NexmoUser
import java.lang.ref.WeakReference

const val PLACEHOLDER = "PLACEHOLDER"
const val TAG = "Nexmo-get-started"

val enabledFeatures = arrayOf(Features.IN_APP_to_IN_APP, Features.PHONE_to_IN_APP, Features.IN_APP_to_PHONE)

enum class Features { IN_APP_to_IN_APP, PHONE_to_IN_APP, IN_APP_to_PHONE }

const val USER_NAME_JANE = "Jane"
const val USER_NAME_JOE = "Joe"
const val USER_ID_JANE = "USR-ab21fbf1-3c30-47c8-acf6-aa739a6ec025" //TODO: swap with the UserId you generated for Jane
const val USER_ID_JOE = "USR-46e8bbd4-0bed-4f6e-8d8d-f11bfe14ff20" //TODO: swap with the UserId you generated for Joe
const val JWT_JANE = "eyJhbGciOiJSUzI1NiJ9.eyJhcHBsaWNhdGlvbl9pZCI6IjY4MDc5ZjM0LWMzNmUtNGUzYi1hNDUxLTIzNTI1ZGE1NDNmYyIsImV4cCI6MTU4MTU2MzU4MiwiaWF0IjoxNTc4OTcxNTgyLCJqdGkiOiIyMjQ4YzZjNy04ZWE3LTRlYmYtYTQ0MS01NTUyYTQ3MTkxY2IiLCJzdWIiOiJKYW5lIiwiYWNsIjp7InBhdGhzIjp7Ii8qL3Nlc3Npb25zLyoqIjp7fSwiLyovdXNlcnMvKioiOnt9LCIvKi9jb252ZXJzYXRpb25zLyoqIjp7fSwiLyovaW1hZ2UvKioiOnt9LCIvKi9tZWRpYS8qKiI6e30sIi8qL2tub2NraW5nLyoqIjp7fSwiLyovcHVzaC8qKiI6e30sIi8qL2RldmljZXMvKioiOnt9LCIvKi9hcHBsaWNhdGlvbnMvKioiOnt9fX19.EKRplZySqZqFYy8CIJls7Id-66zS_A06cefnCifmTEpVQeIZTU6Xw0mjDXlnosntpHyN6dIO45TJeFUZMvZYm5ow9Q8c-sa8wyRRFWpxr0P59JW3dJbzaSkKT8Q8OgMs3Z5Gx95Cja15vVEyUmBsm5di0d8tKiyA7i_EwCAC9ba_SvLtw0U-nQA12sLtKI8ZTp0cTJjKt8ybNEGZlD72YZEd0B7M8BNoSiLS68W-0IRvo8q5kqs7jKWqV8b3VDX11VQdVbLHdyMPc9igeNW2Cni6qEn-V4gb-0NTcScIrmWKLp5J-y5JL0-2wpnv_yG3yk86Z2IoYZ_g8PpYQi-WHg" //TODO: swap with the JWT you generated for Jane
const val JWT_JOE = "eyJhbGciOiJSUzI1NiJ9.eyJhcHBsaWNhdGlvbl9pZCI6IjY4MDc5ZjM0LWMzNmUtNGUzYi1hNDUxLTIzNTI1ZGE1NDNmYyIsImV4cCI6MTU4MTU2MzY4NywiaWF0IjoxNTc4OTcxNjg3LCJqdGkiOiI1NTEyNDY0NS1jMzUwLTQ5NmYtYjQ4Mi00NmNmOWQyMTk4NWEiLCJzdWIiOiJKb2UiLCJhY2wiOnsicGF0aHMiOnsiLyovc2Vzc2lvbnMvKioiOnt9LCIvKi91c2Vycy8qKiI6e30sIi8qL2NvbnZlcnNhdGlvbnMvKioiOnt9LCIvKi9pbWFnZS8qKiI6e30sIi8qL21lZGlhLyoqIjp7fSwiLyova25vY2tpbmcvKioiOnt9LCIvKi9wdXNoLyoqIjp7fSwiLyovZGV2aWNlcy8qKiI6e30sIi8qL2FwcGxpY2F0aW9ucy8qKiI6e319fX0.HgLysYCcvQLZLrc8axxb_rytqgCd-MG8RraT0UTiSd7nYiSWHe6aXxeO2WEr2WHu5iVdL2HA_VvX8kd3aqSPjJlbTE3_ihx4BNQ0ReGh78MS0dMRszXYj9O-Af2G1PMt0AVpneApv8DlvXm9txG3eD25KobmIBTVU5PCk2InyPRjGyHSkmwH7SV05tznc28-dO7e6ZGVjT1rrxEuXylQ9bthiQRaQhw--iPmQ5RMc4eZVw89EEitOgivwfxOXpFjMxuTxU1ayLNLjtQsf_lUrHR749v7j4ZDUGVNsIY6mAokF-RYfUriCIhYtjFQme7n5-8MPN5YWPhRRrCMKFgGXg" //TODO: swap with the JWT you generated for Joe

var currentUser: NexmoUser? = null
var currentCall: NexmoCall? = null
private var didInit: Boolean = false

fun init(appContext: Context) {
    if (didInit) {
        return
    }
    didInit = true
    NexmoClient.Builder()
        .build(appContext)
        .setConnectionListener { status, reason -> Log.d(TAG, "NexmoConnectionListener.ConnectionStatus : $status : $reason") }
}

val userName: String?
    get() = currentUser?.name

val otherUserName: String?
    get() = if (currentUser?.name == USER_NAME_JANE) USER_NAME_JOE else USER_NAME_JANE

val otherUserId: String
    get() = if (currentUser!!.name == USER_NAME_JANE) USER_ID_JOE else USER_ID_JANE

