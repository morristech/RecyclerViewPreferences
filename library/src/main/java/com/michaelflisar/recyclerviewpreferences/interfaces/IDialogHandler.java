package com.michaelflisar.recyclerviewpreferences.interfaces;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;

import com.michaelflisar.recyclerviewpreferences.fragments.SettingsFragment;
import com.michaelflisar.recyclerviewpreferences.implementations.DialogHandler;

import java.util.List;

/**
 * Created by flisar on 25.08.2017.
 */

public interface IDialogHandler<Value> {

    Class<Value> getHandledClass();

    <CLASS,
            SD extends ISettData<Value, CLASS, SD, VH>,
            VH extends RecyclerView.ViewHolder & ISettingsViewHolder<Value, CLASS, SD, VH>>
    boolean handleCustomEvent(DialogHandler.DialogType type, SettingsFragment settingsFragment, final int id, Activity activity, Value value, boolean global, CLASS customSettingsObject);

}
