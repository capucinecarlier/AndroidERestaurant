package fr.isen.carlier.androiderestaurant

import android.bluetooth.BluetoothGattCharacteristic
import android.bluetooth.BluetoothGatt
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup

class BleService(val name: String, val characteritics: MutableList<BluetoothGattCharacteristic>):
    ExpandableGroup<BluetoothGattCharacteristic>(name, characteritics){

}