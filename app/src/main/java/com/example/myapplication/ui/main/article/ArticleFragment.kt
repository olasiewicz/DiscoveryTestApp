package com.example.myapplication.ui.main.article

import android.content.Intent
import android.content.Intent.ACTION_SEND
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentArticleBinding

class ArticleFragment : Fragment() {

    private var _binding: FragmentArticleBinding? = null
    private val binding get() = _binding!!
    lateinit  var args: ArticleFragmentArgs

    private val requestOptions = RequestOptions
        .placeholderOf(R.drawable.default_image)
        .error(R.drawable.default_image)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentArticleBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = arguments ?: return
        args = ArticleFragmentArgs.fromBundle(bundle)
        initWidgets(args)
    }

    private fun initWidgets(args: ArticleFragmentArgs) {
        Glide.with(this)
            .setDefaultRequestOptions(requestOptions)
            .load(args.image)
            .into(binding.imageArticle)

        binding.apply {
            tvTitle.text = args.title
            tvAuthor.text = args.author
            tvDate.text = args.date
            tvTeaser.text = args.teaser
            tvSport.text = args.sportName
            imageBack.setOnClickListener{
               findNavController().navigate(ArticleFragmentDirections.actionArticleFragment2ToHomeFragment())
            }
            imageShare.setOnClickListener {
                val intentShare = Intent(Intent(ACTION_SEND))
                intentShare.apply {
                    type = "text/plant"
                    putExtra(Intent.EXTRA_SUBJECT,"Share")
                }
                startActivity(Intent.createChooser(intentShare, "Sharing"))
            }
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}