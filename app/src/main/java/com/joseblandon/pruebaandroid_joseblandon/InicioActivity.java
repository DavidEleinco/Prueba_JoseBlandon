package com.joseblandon.pruebaandroid_joseblandon;
import android.content.Intent;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class InicioActivity extends AppCompatActivity {
    private String[] opciones;
    private DrawerLayout drawerLayout;
    private ListView listView;
    private ActionBarDrawerToggle drawerToggle;


    private CharSequence tituloSec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        Fragment fragment = new CarteleraFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
//indicamos que se va comenzar una transaccion para colocar dentro del contenedor de frame el Fragment
// que nosotros queremos. y usamos el metodo commit para actualizar el estado

        fragmentManager.beginTransaction().replace(R.id.contenedorFragment, fragment).commit();

        opciones= new String[] {"Cartelera","Vistas","Información USuario","Cerar sesión"};
        drawerLayout= (DrawerLayout) findViewById(R.id.contenedorPrincipal);
        listView = (ListView) findViewById(R.id.menuIzq);

        listView.setAdapter(new ArrayAdapter<String>(getSupportActionBar().getThemedContext(), android.R.layout.simple_list_item_1, opciones));
        final ActionBar ab = getSupportActionBar();
        if (ab !=null) {
            ab.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
            ab.setDisplayHomeAsUpEnabled(true);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fragment fragment = null;
                switch (position) {
                    case 0:
                        fragment = new CarteleraFragment();
                        break;
                    case 1:
                        fragment = new VistosFragment();
                        break;
                    case 2:
                        fragment = new DatosFragment();
                        break;
                    case 3:
                        fragment = new DatosFragment();
                        break;
                }
                FragmentManager fragmentManager = getSupportFragmentManager();
//indicamos que se va comenzar una transaccion para colocar dentro del contenedor de frame el Fragment
// que nosotros queremos. y usamos el metodo commit para actualizar el estado

                fragmentManager.beginTransaction().replace(R.id.contenedorFragment, fragment).commit();
                listView.setItemChecked(position, true);
                tituloSec = opciones[position];
                ab.setTitle(tituloSec);
                drawerLayout.closeDrawer(listView);
            }
        });

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                R.drawable.ic_menu_white_24dp,R.string.abierto,R.string.cerrado);

        drawerLayout.setDrawerListener(drawerToggle);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
