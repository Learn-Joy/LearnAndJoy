package com.mirzahansuslu.learnandjoy
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class CategoriesActivity : AppCompatActivity() {

    private lateinit var categoriesRecyclerView: RecyclerView
    private lateinit var categoriesAdapter: CategoriesAdapter
    private lateinit var firestore: FirebaseFirestore
    private lateinit var categories: MutableList<Category>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)

        categoriesRecyclerView = findViewById(R.id.categoriesRecyclerView)
        categoriesRecyclerView.layoutManager = LinearLayoutManager(this)

        firestore = FirebaseFirestore.getInstance()

        categories = mutableListOf()
        categoriesAdapter = CategoriesAdapter(categories)

        categoriesRecyclerView.adapter = categoriesAdapter

        firestore.collection("categories").get()
            .addOnSuccessListener { result ->
                categories.clear()
                for (document in result.documents) { // loop through all documents in result
                    val category = document.toObject(Category::class.java)
                    category?.let { categories.add(it) }
                }
                categoriesAdapter.notifyDataSetChanged()
            }
            .addOnFailureListener { exception ->
                // Handle exception
            }
    }
}