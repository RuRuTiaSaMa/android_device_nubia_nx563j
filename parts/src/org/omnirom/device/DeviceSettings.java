/*
* Copyright (C) 2016 The OmniROM Project
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 2 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program. If not, see <http://www.gnu.org/licenses/>.
*
*/

package org.omnirom.device;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemProperties;
import android.provider.Settings;
import android.support.v14.preference.PreferenceFragment;
import android.support.v14.preference.SwitchPreference;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceCategory;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.preference.PreferenceScreen;
import android.support.v7.preference.TwoStatePreference;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import org.omnirom.device.R;
import org.omnirom.device.utils.FileUtils;

public class DeviceSettings extends PreferenceFragment implements
        Preference.OnPreferenceChangeListener {

    private static final String KEY_CATEGORY_DISPLAY = "display";
    private static final String KEY_CATEGORY_HW_BUTTONS = "hw_buttons";
    private static final String KEY_CATEGORY_CHARGE = "charge";

    public static final String PALM_SLEEP_KEY = "palm_sleep";
    public static final String PALM_SLEEP_PROP = "persist.gesture.palmsleep";

    public static final String BUTTONS_SWAP_KEY = "buttons_swap";
    public static final String BUTTONS_SWAP_PROP = "persist.gesture.swapbuttons";

    public static final String USB_FASTCHARGE_KEY = "usb_fastcharge";
    public static final String USB_FASTCHARGE_PROP = "persist.charge.usbfastcharge";

    private SwitchPreference mButtonSwap;
    private SwitchPreference mPalmSleep;
    private PreferenceCategory mHWButtons;
    private SwitchPreference mUsbFastcharge;
    private PreferenceCategory mcharge;

    private CharSequence[] mValues;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.main, rootKey);

        PreferenceScreen prefSet = getPreferenceScreen();

        mPalmSleep = (SwitchPreference) findPreference(PALM_SLEEP_KEY);
        mPalmSleep.setChecked(SystemProperties.getBoolean(PALM_SLEEP_PROP, false));
        mPalmSleep.setOnPreferenceChangeListener(this);

        mButtonSwap = (SwitchPreference) findPreference(BUTTON_SWAP_KEY);
        mButtonSwap.setChecked(SystemProperties.getBoolean(BUTTON_SWAP_PROP, false));
        mButtonSwap.setOnPreferenceChangeListener(this);

        mUsbFastcharge = (SwitchPreference) findPreference(USB_FASTCHARGE_KEY);
        mUsbFastcharge.setChecked(SystemProperties.getBoolean(USB_FASTCHARGE_PROP, false));
        mUsbFastcharge.setOnPreferenceChangeListener(this);

}
    private void setPalmSleep(boolean value) {
        SystemProperties.set(PALM_SLEEP_PROP, value ? "1" : "0");
    }

    private void setButtonSwap(boolean value) {
        SystemProperties.set(BUTTON_SWAP_PROP, value ? "1" : "0");
    }

    private void setUsbFastcharge(boolean value) {
        SystemProperties.set(USB_FASTCHARGE_PROP, value ? "1" : "0");
    }

    @Override
    public boolean onPreferenceTreeClick(Preference preference) {
        return super.onPreferenceTreeClick(preference);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        final String key = preference.getKey();

        boolean value;
        String strvalue;

        if (PALM_SLEEP_KEY.equals(key)) {
            value = (Boolean) newValue;
            mPalmSleep.setChecked(value);
            setPalmSleep(value);
            return true;
        } else if (BUTTONS_SWAP_KEY.equals(key)) {
            value = (Boolean) newValue;
            mButtonSwap.setChecked(value);
            setButtonSwap(value);
            return true;
        } else if (USB_FASTCHARGE_KEY.equals(key)) {
            value = (Boolean) newValue;
            mUsbFastcharge.setChecked(value);
            setUsbFastcharge(value);
            return true;
        }
        return true;
    }
}
