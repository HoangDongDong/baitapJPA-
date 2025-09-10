package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import model.Category;
import model.User;
import utils.JPAUtil;

import java.util.Collections;
import java.util.List;

public class CategoryDaoJPA {

    public void save(Category category) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = em.getTransaction();
            transaction.begin();
            em.persist(category);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void update(Category category) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = em.getTransaction();
            transaction.begin();
            em.merge(category);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void delete(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = em.getTransaction();
            transaction.begin();
            Category category = em.find(Category.class, id);
            if (category != null) {
                em.remove(category);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public Category findById(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        Category category = null;
        try {
            category = em.find(Category.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return category;
    }

    public List<Category> findAll() {
        EntityManager em = JPAUtil.getEntityManager();
        List<Category> categories = Collections.emptyList();
        try {
            TypedQuery<Category> query = em.createQuery("SELECT c FROM Category c", Category.class);
            categories = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return categories;
    }

    public List<Category> findCategoriesByUser(User user) {
        EntityManager em = JPAUtil.getEntityManager();
        List<Category> categories = Collections.emptyList();
        try {
            TypedQuery<Category> query = em.createQuery("SELECT c FROM Category c WHERE c.user = :user", Category.class);
            query.setParameter("user", user);
            categories = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return categories;
    }
}