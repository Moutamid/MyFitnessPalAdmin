package com.moutamid.myfitnesspaladmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.Toast;

import com.fxn.stash.Stash;
import com.moutamid.myfitnesspaladmin.databinding.ActivityDetailScreenBinding;
import com.moutamid.myfitnesspaladmin.models.CompModel;
import com.moutamid.myfitnesspaladmin.models.UserModel;
import com.moutamid.myfitnesspaladmin.utili.Constants;

import java.util.HashMap;
import java.util.Map;

public class DetailScreenActivity extends AppCompatActivity {
    ActivityDetailScreenBinding binding;
    String videoUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Constants.changeTheme(this);

        Constants.initDialog(this);
        Constants.showDialog();

        binding.back.setOnClickListener(v -> onBackPressed());

        CompModel model = (CompModel) Stash.getObject(Constants.MODEL, CompModel.class);

        Constants.databaseReference().child(Constants.Users).child(model.getId())
                .get().addOnSuccessListener(snapshot -> {
                    if (snapshot.exists()) {
                        UserModel userModel = snapshot.getValue(UserModel.class);

                        binding.name.setText(userModel.getName());
                        binding.email.setText(userModel.getEmail());
                        binding.id.setText(userModel.getId());

                        binding.squat.setText(model.getSquart() + " Kg");
                        binding.deadLift.setText(model.getDeadLift() + " Kg");
                        binding.bench.setText(model.getBench() + " Kg");
                        videoUrl = model.getVideoUrl();

                        Uri videoUri = Uri.parse(videoUrl);
                        binding.video.setVideoURI(videoUri);
                        MediaController mediaController = new MediaController(this);
                        binding.video.setMediaController(mediaController);
                        mediaController.setAnchorView(binding.video);
                        binding.video.start();
                    }
                    Constants.dismissDialog();
                }).addOnFailureListener(e -> {
                    Constants.dismissDialog();
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                });


        binding.approve.setOnClickListener(v -> approve(model));
        binding.disapprove.setOnClickListener(v -> disapprove(model));

    }

    private void disapprove(CompModel model) {
        Constants.showDialog();
        Constants.databaseReference().child(Constants.Proofs).child(model.getId()).removeValue().addOnSuccessListener(unused1 -> {
            Constants.dismissDialog();
            Toast.makeText(this, "Disapproved", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(DetailScreenActivity.this, MainActivity.class));
            finish();
        }).addOnFailureListener(e -> {
            Constants.dismissDialog();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }

    private void approve(CompModel model) {
        Constants.showDialog();
        Constants.databaseReference().child(Constants.Approved).child(model.getId())
                .setValue(model).addOnSuccessListener(unused -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("rankedAvailable", true);
                    Constants.databaseReference().child(Constants.Users).child(model.getId())
                            .updateChildren(map).addOnSuccessListener(unused2 -> {
                                Constants.databaseReference().child(Constants.Proofs).child(model.getId()).removeValue().addOnSuccessListener(unused1 -> {
                                    Constants.dismissDialog();
                                    Toast.makeText(this, "Approved", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(DetailScreenActivity.this, MainActivity.class));
                                    finish();
                                }).addOnFailureListener(e -> {
                                    Constants.dismissDialog();
                                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                });
                            }).addOnFailureListener(e -> {
                                Constants.dismissDialog();
                                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            });
                }).addOnFailureListener(e -> {
                    Constants.dismissDialog();
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }
}