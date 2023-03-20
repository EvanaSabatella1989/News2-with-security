package com.egg.News2.servicios;

import com.egg.News2.entidades.Noticia;
import com.egg.News2.excepciones.MiException;
import com.egg.News2.repositorios.NoticiaRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticiaServicio {
    
    @Autowired
    private NoticiaRepositorio noticiaRepositorio;
    
    @Transactional
    public void crearNoticia(String titulo, String img, String cuerpo) throws MiException{
        
        validar(titulo, img, cuerpo);
        
        Noticia noticia = new Noticia();
        
        noticia.setTitulo(titulo);
        noticia.setImg(img);
        noticia.setCuerpo(cuerpo);
        
        noticiaRepositorio.save(noticia);
    }
    
    public List<Noticia> listarNoticias() {

        List<Noticia> noticias = new ArrayList();

        noticias = noticiaRepositorio.findAll();

        return noticias;
    }
    
    public Noticia getOne(Long id){
       return noticiaRepositorio.getOne(id);
    }
    
    @Transactional
    public void modificarNoticia(Long id, String titulo, String img, String cuerpo) throws MiException{
        validar(titulo, img, cuerpo);

        Optional<Noticia> respuesta = noticiaRepositorio.findById(id);

        if (respuesta.isPresent()) {

            Noticia noticia = respuesta.get();

            noticia.setTitulo(titulo);
            noticia.setImg(img);
            noticia.setCuerpo(cuerpo);

            noticiaRepositorio.save(noticia);
        }
    }
    
    private void validar(String titulo, String img, String cuerpo) throws MiException {
        
        if (titulo.isEmpty() || titulo == null) {
            throw new MiException("el titulo no puede ser nulo o estar vacio");
        }
        
        if (img.isEmpty() || img == null) {
            throw new MiException("la imagen no puede ser nulo o estar vacio");
        }
        
        if (cuerpo.isEmpty() || cuerpo == null) {
            throw new MiException("el cuerpo no puede ser nulo o estar vacio");
        }
    }
}
