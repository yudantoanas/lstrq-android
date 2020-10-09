package id.listrique

import android.os.Parcel
import android.os.Parcelable

class PLNPower(
    var name: String? = null,
    var value: Int? = null,
    var price: Double? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Double::class.java.classLoader) as? Double
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeValue(value)
        parcel.writeValue(price)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PLNPower> {
        override fun createFromParcel(parcel: Parcel): PLNPower {
            return PLNPower(parcel)
        }

        override fun newArray(size: Int): Array<PLNPower?> {
            return arrayOfNulls(size)
        }
    }
}