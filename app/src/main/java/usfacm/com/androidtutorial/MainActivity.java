package usfacm.com.androidtutorial;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    // Declaring variables in the class scope so that it can be reused in other methods
    private ArrayList<String> items;
    // ArrayAdapter is needed to properly insert the items into the ListView
    private ArrayAdapter<String> itemsAdapter;
    private ListView lvItems;
    Button addItemButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Defining the variables to connect them to the UI
        lvItems = (ListView) findViewById(R.id.lvItems);
        items = new ArrayList<String>();
        itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        lvItems.setAdapter(itemsAdapter);
        items.add("First Item");
        items.add("Second Item");
        addItemButton = (Button) findViewById(R.id.addItemButton);

        // Event listener for removing items from listView
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View item, int pos, long id) {
                final int position = pos;
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setTitle("Confirm delete?");
                alertDialog.setMessage("Are you sure you want to delete this?");
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Delete", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        items.remove(position);
                        itemsAdapter.notifyDataSetChanged();
                        CharSequence deleteToastText = "Deleted item successfully!";
                        Toast deleteToast = Toast.makeText(MainActivity.this, deleteToastText, Toast.LENGTH_SHORT);
                        deleteToast.show();
                    }
                });
                alertDialog.show();
                return true;
            }
        });

        }

    // Adding event listener to addItemButton via XML way
    public void onAddItem(View v) {
        EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
        String itemText = etNewItem.getText().toString();
        itemsAdapter.add(itemText);
        etNewItem.setText("");
        Context context = getApplicationContext();
        CharSequence text = "Added item " + itemText + " to to-do list";
        int duration = Toast.LENGTH_SHORT;
        Toast.makeText(context, text, duration).show();

    }


}