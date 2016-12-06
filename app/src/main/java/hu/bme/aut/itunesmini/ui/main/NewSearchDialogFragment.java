package hu.bme.aut.itunesmini.ui.main;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import hu.bme.aut.itunesmini.R;
import hu.bme.aut.itunesmini.model.SearchItem;
import hu.bme.aut.itunesmini.ui.result.ResultActivity;

/**
 * Created by matepapp on 2016. 12. 05..
 */

public class NewSearchDialogFragment extends AppCompatDialogFragment {
    public static final String TAG = "NewSearchDialogFragment";
    private AddSearchItemDialogListener listener;
    private EditText expressionEditText;
    private EditText resultCountEditText;
    private Button plusButton;
    private Button minusButton;
    private Spinner typeSpinner;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getActivity() instanceof AddSearchItemDialogListener) {
            listener = (AddSearchItemDialogListener) getActivity();
        } else {
            throw new RuntimeException(
                    "Activity must implement AddSearchItemDialogListener interface!");
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
                                SearchItem searchItem = getSearchItem();
                                listener.onSearchItemAdded(searchItem);

                                Intent showResultsIntent = new Intent();
                                showResultsIntent.setClass(
                                        getActivity().getBaseContext(), ResultActivity.class);
                                showResultsIntent.putExtra(
                                        ResultActivity.QUERY, searchItem.expression);
                                showResultsIntent.putExtra(
                                        ResultActivity.MEDIA, searchItem.type);
                                showResultsIntent.putExtra(
                                        ResultActivity.LIMIT, searchItem.resultCount);
                                startActivity(showResultsIntent);
                            }
                        })
                .setNegativeButton(R.string.back, null).create();
    }

    private View getContentView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_new_search, null);
        expressionEditText = (EditText) view.findViewById(R.id.ExpressionEditText);
        resultCountEditText = (EditText) view.findViewById(R.id.ResultsCountEditText);
        plusButton = (Button) view.findViewById(R.id.Plus);
        minusButton = (Button) view.findViewById(R.id.Minus);
        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer result = Integer.parseInt(resultCountEditText.getText().toString());
                result++;
                resultCountEditText.setText(result.toString());
            }
        });

        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer result = Integer.parseInt(resultCountEditText.getText().toString());
                result--;
                resultCountEditText.setText(result.toString());
            }
        });

        typeSpinner = (Spinner) view.findViewById(R.id.SearchItemTypeSpinner);
        typeSpinner.setAdapter(new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.type_items))
        );

        return view;
    }

    private SearchItem getSearchItem() {
        SearchItem searchItem = new SearchItem();
        searchItem.expression = expressionEditText.getText().toString();
        searchItem.type = SearchItem.Type.typeOf(typeSpinner.getSelectedItem().toString());

        try {
            searchItem.resultCount = Integer.parseInt(resultCountEditText.getText().toString());
        } catch (NumberFormatException e) {
            searchItem.resultCount = 0;
        }

        searchItem.save();

        return searchItem;
    }
}
