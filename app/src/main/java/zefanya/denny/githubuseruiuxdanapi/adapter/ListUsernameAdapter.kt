package zefanya.denny.githubuseruiuxdanapi.adapter

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import zefanya.denny.githubuseruiuxdanapi.R
import zefanya.denny.githubuseruiuxdanapi.data.source.remote.response.ItemsItem
import zefanya.denny.githubuseruiuxdanapi.databinding.ItemUserBinding
import zefanya.denny.githubuseruiuxdanapi.util.ClickItemRvCallBack

class ListUsernameAdapter(private val clickItemRvCallBack: ClickItemRvCallBack) :
    RecyclerView.Adapter<ListUsernameAdapter.UsersViewHolder>() {

    private var listData = ArrayList<ItemsItem?>()

    fun setData(newListData: List<ItemsItem>?) {
        listData.clear()
        if(newListData!=null)
            listData.addAll(newListData!!)
        notifyDataSetChanged()
    }

    inner class UsersViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(userEntity: ItemsItem) {
            with(binding) {
                tvUsername.text = itemView.resources.getString(R.string.f_username,userEntity.login)
                tvType.text = itemView.resources.getString(R.string.f_type,userEntity.type)
                Glide.with(itemView.context)
                    .asBitmap()
                    .load("https://avatars.githubusercontent.com/u/" + userEntity.id)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_baseline_error_24)
                    )
                    .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(
                    resource: Bitmap,
                    transition: Transition<in Bitmap>?
                ) {
                    ivAvatar.setImageBitmap(resource)

                    Palette.from(resource).generate { palette ->
                        val defValue = itemView.resources.getColor(
                            R.color.dark,
                            itemView.context.theme
                        )
                        cardItem.setCardBackgroundColor(
                            palette?.getDarkMutedColor(defValue) ?: defValue
                        )
                    }
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                }
            })
                itemView.setOnClickListener{
                    clickItemRvCallBack.onItemClick(userEntity.login!!)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val view  = ItemUserBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return UsersViewHolder(view)
    }


    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val user = listData[position]
        if(user !=null)
            holder.bind(user)
    }

    override fun getItemCount() = listData.count()

}