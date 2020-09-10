package com.fasoh.trialproject.flow.lists

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fasoh.trialproject.R
import com.fasoh.trialproject.model.TopHourLearner
import com.fasoh.trialproject.model.TopSkillIqLearner

private val TAB_TITLES = arrayOf(
    R.string.tab_text_1,
    R.string.tab_text_2
)


class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return LearnersFragment.newInstance(position + 1)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return 2
    }
}

class TopLearnerIqAdapter (private val items : List<TopSkillIqLearner>): RecyclerView.Adapter<TopLearnerIqAdapter.IqLearnerViewHolder>(){
    class IqLearnerViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val icon = view.findViewById<ImageView>(R.id.imageView)
        private val name = view.findViewById<TextView>(R.id.name)
        private val detail = view.findViewById<TextView>(R.id.detail)


        fun bind(item: TopSkillIqLearner) {
            name.text = item.name
            detail.text =  "${item.score} SkillIQ Score, ${item.country}"
            Glide
                .with(icon.context)
                .load(item.badgeUrl)
                .centerCrop()
                .into(icon);
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): IqLearnerViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return IqLearnerViewHolder(inflater.inflate(R.layout.list_item_top_hours, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: IqLearnerViewHolder, position: Int) {
        holder.bind(items[position])
    }
}

class TopLearnerHoursAdapter (private val items : List<TopHourLearner>): RecyclerView.Adapter<TopLearnerHoursAdapter.HourLearnerViewHolder>(){
    class HourLearnerViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val icon = view.findViewById<ImageView>(R.id.imageView)
        private val name = view.findViewById<TextView>(R.id.name)
        private val detail = view.findViewById<TextView>(R.id.detail)


        fun bind(item: TopHourLearner) {
            name.text = item.name
            detail.text =  "${item.hours} learning hours, ${item.country}"
            Glide
                .with(icon.context)
                .load(item.badgeUrl)
                .centerInside()
                .into(icon);
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HourLearnerViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return HourLearnerViewHolder(inflater.inflate(R.layout.list_item_top_skill, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: HourLearnerViewHolder, position: Int) {
        holder.bind(items[position])
    }
}