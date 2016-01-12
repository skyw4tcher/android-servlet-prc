package ro.prc.android.activities;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ro.prc.android.R;
import ro.prc.android.service.Service;

public class CnpActivity extends AppCompatActivity {

    private EditText inputName, inputSurName, inputCnp;
    private TextInputLayout inputLayoutName, inputLayoutSurName, inputLayoutCnp;
    private Button btnCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cnp);

        inputLayoutName = (TextInputLayout) findViewById(R.id.input_layout_name);
        inputLayoutSurName = (TextInputLayout) findViewById(R.id.input_layout_surname);
        inputLayoutCnp = (TextInputLayout) findViewById(R.id.input_layout_cnp);
        inputName = (EditText) findViewById(R.id.input_name);
        inputSurName = (EditText) findViewById(R.id.input_surname);
        inputCnp = (EditText) findViewById(R.id.input_cnp);
        btnCheck = (Button) findViewById(R.id.btn_check);

        inputName.addTextChangedListener(new MyTextWatcher(inputName));
        inputSurName.addTextChangedListener(new MyTextWatcher(inputSurName));
        inputCnp.addTextChangedListener(new MyTextWatcher(inputCnp));

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitForm();
            }
        });
    }

    private void submitForm() {
        if (!validateName()) {
            return;
        }

        if (!validateSurName()) {
            return;
        }

        if (!validateCnp()) {
            return;
        }

        Service.makeGetRequest();

        Toast.makeText(getApplicationContext(), "Success!", Toast.LENGTH_SHORT).show();
    }

    private boolean validateCnp() {

        if (inputCnp.getText().toString().trim().isEmpty()) {
            inputLayoutCnp.setError(getString(R.string.err_msg_empty_cnp));
            requestFocus(inputCnp);
            return false;
        //} if () {

        } else {
            inputLayoutCnp.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateName() {
        if (inputName.getText().toString().trim().isEmpty()) {
            inputLayoutName.setError(getString(R.string.err_msg_empty_name));
            requestFocus(inputName);
            return false;
        } else {
            inputLayoutName.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateSurName() {
        if (inputSurName.getText().toString().trim().isEmpty()) {
            inputLayoutSurName.setError(getString(R.string.err_msg_empty_surname));
            requestFocus(inputSurName);
            return false;
        } else {
            inputLayoutSurName.setErrorEnabled(false);
        }

        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.input_name:
                    validateName();
                    break;
                case R.id.input_surname:
                    validateSurName();
                    break;
                case R.id.input_cnp:
                    validateCnp();
                    break;
            }
        }
    }

}
