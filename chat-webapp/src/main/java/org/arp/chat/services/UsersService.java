package org.arp.chat.services;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.arp.chat.entities.Role;
import org.arp.chat.entities.User;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsersService {

    @Inject
    EntityManager em;
    private MessageDigest digest;

    @GET
    public List<User> findAllUsers() {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
        return query.getResultList();
    }

    @POST
    public User createUser(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Role guestRole = em.find(Role.class, 2L);
        user.setRoles(Arrays.asList(guestRole));
        user.setPassword(calculateMd5(user.getPassword()));
        
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        return user;
    }

    private String calculateMd5(String password) throws NoSuchAlgorithmException {
        byte[] bytes = password.getBytes(Charset.forName("UTF-8"));
        byte[] digest = getMessageDigest().digest(bytes);
        String hexString = new BigInteger(1, digest).toString(16);
        return String.format("MD5:%32s", hexString).replace(' ', '0');
    }

    private synchronized MessageDigest getMessageDigest() throws NoSuchAlgorithmException {
        if (digest == null) {
            digest = MessageDigest.getInstance("MD5");
        }
        return digest;
    }

}
