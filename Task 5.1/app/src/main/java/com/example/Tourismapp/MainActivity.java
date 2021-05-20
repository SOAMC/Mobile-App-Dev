package com.example.tourismapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements com.example.tourismapp.VerticalAdapter.OnRowClickListener{
    RecyclerView recyclerViewHorizontal;
    HorizontalAdapter horizontalAdapter;
    List<Horizontal> HorizontalList = new ArrayList<>();
    Integer[] imageListH = {R.drawable.amritsar, R.drawable.delhi, R.drawable.mumbai, R.drawable.jaipur, R.drawable.agra};

    RecyclerView recyclerViewVertical;
    VerticalAdapter verticalAdapter;
    List<Vertical> VerticalList = new ArrayList<>();

    Integer[] imageList = {R.drawable.amritsar, R.drawable.delhi, R.drawable.mumbai, R.drawable.jaipur, R.drawable.agra};
    String[] nameList = {"Amritsar","Delhi","Mumbai","Jaipur","Agra"};
    String[] descripList = {"Amritsar is an important hub of Sikh history and culture. The main attraction here is Harmandir Sahib, opened in 1604 and still often referred to as the Golden Temple for its beautiful gold decoration.",
        "Red fort in delhi,Built by Shah Jahan in 1648 as the seat of Mughal power-a role it maintained until 1857-the magnificent crescent-shaped Red Fort in New Delhi, named after the stunning red sandstone used in its construction.",
        "Gateway of india, Standing an impressive 26 meters tall and overlooking the Arabian Sea, the iconic Gateway of India is a must-see when in Mumbai. Built to commemorate the arrival of King George V and his wife Queen Mary in 1911, this stunning piece of architecture was opened with much pomp and ceremony in 1924 and was, for a while, the tallest structure in the city.",
        "The Golden City of Jaisalmer is an oasis of splendid old architecture that rises from the sand dunes of the Thar Desert. Once a strategic outpost, today the city is filled with splendid old mansions, magnificent gateways, and the massive Jaisalmer Fort-also known as the Golden Fort.",
        "Agra, Perhaps India's most recognizable building, the Taj Mahal is also the world's most famous testimony to the power of love. Named after Mumtaz Mahal, the favorite wife of Emperor Shah Jahan, this most beautiful of mausoleums was begun upon her death in 1631 and took 20,000 workmen until 1648 to complete."};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewHorizontal = findViewById(R.id.Horizontal_view);
        horizontalAdapter = new HorizontalAdapter(HorizontalList, this);
        recyclerViewHorizontal.setAdapter(horizontalAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewHorizontal.setLayoutManager(layoutManager);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        for (int i = 0; i < imageListH.length; i++){
            com.example.tourismapp.Horizontal horizontal = new com.example.tourismapp.Horizontal(i, imageListH[i]);
            HorizontalList.add(horizontal);
        }

        recyclerViewVertical = findViewById(R.id.Vertical_view);
        verticalAdapter = new VerticalAdapter(VerticalList, MainActivity.this.getApplicationContext(), this);
        recyclerViewVertical.setAdapter(verticalAdapter);
        recyclerViewVertical.setLayoutManager(new LinearLayoutManager(this));

        for (int i = 0; i < imageList.length; i++){
            com.example.tourismapp.Vertical vertical = new com.example.tourismapp.Vertical(i,imageList[i],nameList[i],descripList[i]);
            VerticalList.add(vertical);
        }
    }

    @Override
    public void onItemClick(int position) {
        Fragment fragment;
        switch (position){
            case 0:
                fragment = new Fragment1();
                break;
            case 1:
                fragment = new Fragment2();
                break;
            case 2:
                fragment = new Fragment3();
                break;
            case 3:
                fragment = new Fragment4();
                break;
            case 4:
                fragment = new Fragment5();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + position);
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.Fragment, fragment).commit();
    }
}
