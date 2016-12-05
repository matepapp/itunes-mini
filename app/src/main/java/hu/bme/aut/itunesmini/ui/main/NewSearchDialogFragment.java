package hu.bme.aut.itunesmini.ui.main;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import hu.bme.aut.itunesmini.R;

/**
 * Created by matepapp on 2016. 12. 05..
 */

public class NewSearchDialogFragment extends AppCompatDialogFragment {
    public static final String TAG = "NewSearchDialogFragment";
    private AddExpressionDialogListener listener;
    private EditText editText;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getActivity() instanceof AddExpressionDialogListener) {
            listener = (AddExpressionDialogListener) getActivity();
        } else {
            throw new RuntimeException(
                    "Activity must implement AddExpressionDialogListener interface!");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getContext()).setTitle(R.string.new_search)
                .setView(getContentView())
                .setPositiveButton(R.string.search,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                listener.onExpressionAdded(editText.getText().toString());
                            }
                        })
                .setNegativeButton(R.string.back, null).create();
    }

    private View getContentView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_new_search, null);
        editText = (EditText) view.findViewById(R.id.NewSearchDialogEditText);
        return view;
    }
}
