package cn.ocookie.entity;

import java.util.List;

public class Posts {

    private List<Post> posts;

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public static class Post {

        private String title;
        private String slug;
        private String markdown;
        private String image;
        private boolean featured;
        private boolean page;
        private String status;
        private String language;
        private String meta_title;
        private String meta_description;
        private String author;
        private String published_by;
        private List<String> tags;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSlug() {
            return slug;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }

        public String getMarkdown() {
            return markdown;
        }

        public void setMarkdown(String markdown) {
            this.markdown = markdown;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public boolean getFeatured() {
            return featured;
        }

        public void setFeatured(boolean featured) {
            this.featured = featured;
        }

        public boolean getPage() {
            return page;
        }

        public void setPage(boolean page) {
            this.page = page;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public String getMeta_title() {
            return meta_title;
        }

        public void setMeta_title(String meta_title) {
            this.meta_title = meta_title;
        }

        public String getMeta_description() {
            return meta_description;
        }

        public void setMeta_description(String meta_description) {
            this.meta_description = meta_description;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getPublished_by() {
            return published_by;
        }

        public void setPublished_by(String published_by) {
            this.published_by = published_by;
        }

        public List<String> getTags() {
            return tags;
        }

        public void setTags(List<String> tags) {
            this.tags = tags;
        }

    }

}