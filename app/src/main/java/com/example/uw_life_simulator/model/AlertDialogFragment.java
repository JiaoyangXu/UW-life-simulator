package com.example.uw_life_simulator.model;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import android.content.DialogInterface;
import android.app.AlertDialog;

import android.app.Dialog;

import com.example.uw_life_simulator.R;
public class AlertDialogFragment extends DialogFragment {

    public interface AlertDialogListener {
        public void onOKClick(DialogFragment dialog);
        public void onCancelClick(DialogFragment dialog);
    }
    AlertDialogListener mlistener;
    public void setListener(AlertDialogListener mListener) {
        // now u set value on your listener
        this.mlistener = mListener;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.proceed_to_summary)
                .setTitle("END OF TERM")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Send the TryAgain button event back to the host fragment
                        mlistener.onOKClick(AlertDialogFragment.this);
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mlistener.onCancelClick(AlertDialogFragment.this);
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
