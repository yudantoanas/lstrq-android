package id.listrique

import android.os.Parcel
import android.os.Parcelable

class CalculatePower(
    var plnPower: PLNPower? = null,
    var deviceList: List<Device>? = emptyList()
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(PLNPower::class.java.classLoader),
        parcel.createTypedArrayList(Device)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(plnPower, flags)
        parcel.writeTypedList(deviceList)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CalculatePower> {
        override fun createFromParcel(parcel: Parcel): CalculatePower {
            return CalculatePower(parcel)
        }

        override fun newArray(size: Int): Array<CalculatePower?> {
            return arrayOfNulls(size)
        }
    }
}