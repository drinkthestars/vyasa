package com.goofy.goober.ui

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.Text
import androidx.ui.layout.Column
import androidx.ui.layout.Spacer
import androidx.ui.layout.height
import androidx.ui.layout.padding
import androidx.ui.material.MaterialTheme
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import com.goofy.goober.domain.model.Excerpt
import com.goofy.goober.domain.model.Post
import com.goofy.goober.domain.model.Title

@Composable
fun PostRow(post: Post) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(text = post.title.rendered, style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(14.dp))
        Text(text = post.excerpt.rendered, style = MaterialTheme.typography.body2)
    }
}

@Preview
@Composable
fun DefaultPreview() {
    MaterialTheme {
        PostRow(
            Post(
                excerpt = Excerpt("Carefully design your queries to pull in only the needed properties from each resource to make your application faster to use and more efficient to run."),
                title = Title("This is a Title"),
                id = 1_000,
                link = "www.go.com"
            )
        )
    }
}
