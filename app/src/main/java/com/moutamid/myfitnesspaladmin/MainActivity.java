package com.moutamid.myfitnesspaladmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.moutamid.myfitnesspaladmin.adapters.RankAdapter;
import com.moutamid.myfitnesspaladmin.databinding.ActivityMainBinding;
import com.moutamid.myfitnesspaladmin.models.CompModel;
import com.moutamid.myfitnesspaladmin.utili.Constants;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    ArrayList<CompModel> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Constants.changeTheme(this);
        Constants.checkApp(this);

        Constants.initDialog(this);
        Constants.showDialog();

        list = new ArrayList<>();

        binding.rankRC.setLayoutManager(new LinearLayoutManager(this));
        binding.rankRC.setHasFixedSize(false);

        Constants.databaseReference().child(Constants.Proofs)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                                CompModel model = dataSnapshot.getValue(CompModel.class);
                                list.add(model);
                            }
                            list.sort((person1, person2) -> Integer.compare(person2.getTotalPoints(), person1.getTotalPoints()));

                            for (int i = 0; i < list.size(); i++) {
                                CompModel person = list.get(i);
                                int rank = i + 1;
                                person.setRank(rank);
                            }

                            RankAdapter adapter = new RankAdapter(MainActivity.this,MainActivity.this, list);
                            binding.rankRC.setAdapter(adapter);
                        }
                        Constants.dismissDialog();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Constants.dismissDialog();
                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }
}