package com.example.a22_2_sejong_project.utils

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.a22_2_sejong_project.DTO.BoardContentDTO
import com.example.a22_2_sejong_project.MainActivity
import com.example.a22_2_sejong_project.R
import com.example.a22_2_sejong_project.board.BoardFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.fragment_add_board_article.*

import kotlinx.android.synthetic.main.fragment_add_board_article.view.*
import java.text.SimpleDateFormat
import java.util.*


class AddBoardArticleFragment : Fragment() {
    private var storage: FirebaseStorage? = null
    private var firestore: FirebaseFirestore? = null
    var auth: FirebaseAuth? = null
    var type: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val rootView = inflater.inflate(R.layout.fragment_add_board_article, container, false)

        // Initiate
        storage = FirebaseStorage.getInstance()
        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        rootView.add_article_btn_upload.setOnClickListener {
            contentUpload()
        }

        rootView.radio_button_group.setOnCheckedChangeListener{ group, checkedId ->
            when(checkedId) {
                R.id.radio_recruit -> {
                    type = 1
                    headcount_textView.text = "모집 인원: "
                }
                R.id.radio_participate -> {
                    type = 2
                    headcount_textView.text = "참여 인원: "
                }
                R.id.radio_etc -> {
                    type = 3
                    headcount_textView.text = "기타 인원: "
                }
            }
        }


        return rootView
    }

    fun contentUpload() {
        var timestamp = SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(Date())
        var boardContentDTO = BoardContentDTO()

        //var storageRef = storage?.reference?.child("boardContents")?.child(fileName)

        boardContentDTO.title = add_article_title.text.toString()
        boardContentDTO.description = add_article_description.text.toString()
        boardContentDTO.timestamp = timestamp
        boardContentDTO.contentType = type!!

        if(headcount_editText.toString().equals("")) boardContentDTO.totalHeadCount = -1
        else boardContentDTO.totalHeadCount = headcount_editText.text.toString().toInt()



        firestore?.collection("boardContents")?.document()?.set(boardContentDTO)

        (activity as MainActivity).replaceFragment(BoardFragment())
    }
}