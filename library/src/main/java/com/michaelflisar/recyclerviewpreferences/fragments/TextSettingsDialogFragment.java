package com.michaelflisar.recyclerviewpreferences.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.text.InputType;

import com.afollestad.materialdialogs.MaterialDialog;
import com.evernote.android.state.State;
import com.evernote.android.state.StateSaver;
import com.michaelflisar.bundlebuilder.Arg;
import com.michaelflisar.bundlebuilder.BundleBuilder;
import com.michaelflisar.recyclerviewpreferences.SettingsManager;

/**
 * Created by flisar on 22.08.2017.
 */

@BundleBuilder(setterPrefix = "with", useConstructorForMandatoryArgs = true)
public class TextSettingsDialogFragment extends DialogFragment {

    @Arg
    Integer id;
    @Arg
    Boolean global;
    @Arg
    String value;
    @Arg
    String title;
    @Arg
    Boolean allowEmptyInput;

    @State
    String lastValue;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StateSaver.restoreInstanceState(this, savedInstanceState);
        TextSettingsDialogFragmentBundleBuilder.inject(getArguments(), this);

        if (lastValue != null) {
            value = lastValue;
        } else {
            lastValue = value;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        StateSaver.saveInstanceState(this, outState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        MaterialDialog dlg = new MaterialDialog.Builder(getActivity())
                .title(title)
                .input(null, value, allowEmptyInput, (materialDialog, charSequence) -> {
                    lastValue = charSequence.toString();
                })
                .alwaysCallInputCallback()
                .inputType(InputType.TYPE_CLASS_TEXT)
                .positiveText(android.R.string.ok)
                .onPositive((materialDialog, dialogAction) -> {
                    SettingsManager.get().dispatchTextChanged(id, getActivity(), lastValue, global);
                })
                .show();
        return dlg;
    }
}
