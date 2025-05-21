package ru.mirea.shabanovrv.employeedb;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Database;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.PrimaryKey;
import androidx.room.Query;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    // --- Entity ---
    @Entity(tableName = "superhero")
    public static class Superhero {
        @PrimaryKey(autoGenerate = true)
        public long id;
        public String name;
        public String superpower;

    }
    // --- DAO ---
    @Dao
    public interface SuperheroDao {
        @Query("SELECT * FROM superhero")
        List<Superhero> getAll();
        @Query("SELECT * FROM superhero WHERE id = :id")
        Superhero getById(long id);
        @Insert
        void insert(Superhero superhero);
        @Update
        void update(Superhero superhero);
        @Delete
        void delete(Superhero superhero);
    }
    // --- Database ---
    @Database(entities = {Superhero.class}, version = 1)
    public abstract static class AppDatabase extends RoomDatabase {
        public abstract SuperheroDao superheroDao();
    }
    private AppDatabase db;
    private SuperheroDao dao;
    private EditText heroname, herotext;
    private Button save, update, delete;
    private RecyclerView recyclerView;
    private SuperheroAdapter adapter;
    private long selectedHeroId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Ваш layout

        db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "superheroes_db")
                .allowMainThreadQueries()
                .build();
        dao = db.superheroDao();

        heroname = findViewById(R.id.heroname);
        herotext = findViewById(R.id.herotext);
        save = findViewById(R.id.save);
        update = findViewById(R.id.update);
        delete = findViewById(R.id.delete);
        recyclerView = findViewById(R.id.rv_superheroes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SuperheroAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        loadHeroesToList();

        save.setOnClickListener(v -> {
            String name = heroname.getText().toString().trim();
            String power = herotext.getText().toString().trim();

            if (name.isEmpty()) {
                Toast.makeText(this, "Введите имя героя", Toast.LENGTH_LONG).show();
                return;
            }
            Superhero hero = new Superhero();
            hero.name = name;
            hero.superpower = power;

            dao.insert(hero);
            Toast.makeText(this, "Герой добавлен", Toast.LENGTH_LONG).show();
            clearFields();
            loadHeroesToList();
        });

        update.setOnClickListener(v -> {
            if (selectedHeroId == -1) {
                Toast.makeText(this, "Выберите героя из списка для обновления", Toast.LENGTH_LONG).show();
                return;
            }

            String name = heroname.getText().toString().trim();
            String power = herotext.getText().toString().trim();

            if (name.isEmpty()) {
                Toast.makeText(this, "Введите имя героя", Toast.LENGTH_LONG).show();
                return;
            }

            Superhero hero = dao.getById(selectedHeroId);
            if (hero == null) {
                Toast.makeText(this, "Герой не найден", Toast.LENGTH_LONG).show();
                return;
            }

            hero.name = name;
            hero.superpower = power;


            dao.update(hero);
            Toast.makeText(this, "Герой обновлён", Toast.LENGTH_LONG).show();
            clearFields();
            selectedHeroId = -1;
            loadHeroesToList();
        });

        delete.setOnClickListener(v -> {
            if (selectedHeroId == -1) {
                Toast.makeText(this, "Выберите героя из списка для удаления", Toast.LENGTH_LONG).show();
                return;
            }

            Superhero hero = dao.getById(selectedHeroId);
            if (hero == null) {
                Toast.makeText(this, "Герой не найден", Toast.LENGTH_LONG).show();
                return;
            }

            dao.delete(hero);
            Toast.makeText(this, "Герой удалён", Toast.LENGTH_LONG).show();
            clearFields();
            selectedHeroId = -1;
            loadHeroesToList();
        });
    }

    private void loadHeroesToList() {
        List<Superhero> heroes = dao.getAll();
        adapter.setHeroes(heroes);
    }

    private void clearFields() {
        heroname.setText("");
        herotext.setText("");

    }

    private class SuperheroAdapter extends RecyclerView.Adapter<SuperheroAdapter.HeroViewHolder> {

        private List<Superhero> heroes;
        public SuperheroAdapter(List<Superhero> heroes) {
            this.heroes = heroes;
        }
        public void setHeroes(List<Superhero> heroes) {
            this.heroes = heroes;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public HeroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(android.R.layout.simple_list_item_1, parent, false);
            return new HeroViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull HeroViewHolder holder, int position) {
            Superhero hero = heroes.get(position);
            holder.bind(hero);
        }

        @Override
        public int getItemCount() {
            return heroes.size();
        }

        class HeroViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            private android.widget.TextView textView;
            private Superhero currentHero;
            public HeroViewHolder(@NonNull View itemView) {
                super(itemView);
                textView = itemView.findViewById(android.R.id.text1);
                itemView.setOnClickListener(this);
            }
            void bind(Superhero hero) {
                currentHero = hero;
                textView.setText(hero.name + "");
            }
            @Override
            public void onClick(View v) {
                selectedHeroId = currentHero.id;
                heroname.setText(currentHero.name);
                herotext.setText(currentHero.superpower);
                Toast.makeText(MainActivity.this, "Герой выбран: " + currentHero.name, Toast.LENGTH_LONG).show();
            }
        }
    }
}