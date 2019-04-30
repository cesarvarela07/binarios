package app311.varela.cesar.app311;

public class NewsPost {

    String noticia,
    categoria,
    compania;

    int img;

    public NewsPost(String noticia, String categoria, String compania, int img) {
        this.noticia = noticia;
        this.categoria = categoria;
        this.compania = compania;
        this.img = img;
    }

    public String getNoticia() {
        return noticia;
    }

    public void setNoticia(String noticia) {
        this.noticia = noticia;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
