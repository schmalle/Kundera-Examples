/*
 * Copyright 2011 Impetus Infotech.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.impetus.kundera.examples.pickr;

import java.util.List;

import junit.framework.TestCase;

import com.impetus.kundera.examples.pickr.dao.Pickr;
import com.impetus.kundera.examples.pickr.dao.PickrImpl;
import com.impetus.kundera.examples.pickr.entities.Album;
import com.impetus.kundera.examples.pickr.entities.PersonalData;
import com.impetus.kundera.examples.pickr.entities.Photo;
import com.impetus.kundera.examples.pickr.entities.Photographer;

/**
 * Test case for Pickr application
 * 
 * @author amresh.singh
 */
public class PickrTest extends TestCase
{
    Pickr pickr;

    String photographerId;

    protected void setUp() throws Exception
    {
        super.setUp();
        photographerId = "1";
        pickr = new PickrImpl("piccandra,picongo,picbase");
    }

    public void test()
    {
        addPhotographerAlbumsAndPhotos();
        getPhotographer();
        getAllPhotographers();

        pickr.close();
    }

    public void addPhotographerAndAlbums()
    {
        Photographer p = new Photographer();
        p.setPhotographerId(photographerId);
        p.setPersonalData(new PersonalData("Amresh", "amresh.singh@impetus.co.in", "Noida"));
        p.addAlbum(new Album("a", "My Phuket Vacation", "Went Phuket with friends"));
        p.addAlbum(new Album("b", "Office Pics", "Annual office party photos"));

        pickr.addPhotographer(p);
    }
    
    public void addPhotographerAlbumsAndPhotos()
    {
        Photographer p = new Photographer();
        p.setPhotographerId(photographerId);
        p.setPersonalData(new PersonalData("Amresh", "amresh.singh@impetus.co.in", "Noida"));
        
        Album album1 = new Album("a", "My Phuket Vacation", "Went Phuket with friends"); 
        album1.addPhoto(new Photo("a1", "One beach", "On beach with friends"));
        album1.addPhoto(new Photo("a2", "In Hotel", "Chilling out in room"));
        album1.addPhoto(new Photo("a3", "At Airport", "So tired"));
        
        
        Album album2 = new Album("b", "Office Pics", "Annual office party photos");
        album2.addPhoto(new Photo("b1", "Office Team event", "Shot at Fun park"));
        album2.addPhoto(new Photo("b2", "My Team", "My team is the best"));
                
        p.addAlbum(album1);
        p.addAlbum(album2);

        pickr.addPhotographer(p);
    }


    public void addPhotographer()
    {
        Photographer p = new Photographer();
        p.setPhotographerId(photographerId);

        p.setPersonalData(new PersonalData("Amresh", "amresh.singh@impetus.co.in", "Noida"));
        
        pickr.addPhotographer(p);
    }

    public void createAlbums()
    {
        pickr.createAlbum("a", "My Phuket Vacation", "Went Phuket with friends");
        pickr.createAlbum("b", "Office Pics", "Annual office party photos");
    }

    public void addPhotoToAlbum()
    { // fail("Not yet implemented");

    }

    public void getPhotographer()
    {
        Photographer p = pickr.getPhotographer(photographerId);

        assertNotNull(p);
        assertEquals("1", p.getPhotographerId());
        assertNotNull(p.getPersonalData());
        assertEquals("Amresh", p.getPersonalData().getName());
        assertNotNull(p.getAlbums());
        assertFalse(p.getAlbums().isEmpty());
        assertEquals(2, p.getAlbums().size());
        
        Album album1 = p.getAlbums().get(0);
        assertNotNull(album1);
        assertEquals(1, album1.getAlbumId().length());
        assertFalse(album1.getAlbumName().length() == 0);
        assertFalse(album1.getAlbumDescription().length() == 0);
        
        List<Photo> album1Photos = album1.getPhotos();
        assertNotNull(album1Photos);
        assertFalse(album1Photos.isEmpty());
        assertFalse(album1Photos.size() < 2);
        
        Photo album1Photo1 = album1Photos.get(0);
        assertNotNull(album1Photo1);
        assertEquals(2, album1Photo1.getPhotoId().length());       

    }

    public void getAllPhotographers()
    {
        List<Photographer> photographers = pickr.getAllPhotographers();

        assertNotNull(photographers);
        assertFalse(photographers.isEmpty());
        assertEquals(1, photographers.size());
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

}
