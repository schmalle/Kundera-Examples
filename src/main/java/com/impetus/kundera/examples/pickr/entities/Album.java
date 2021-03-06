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
package com.impetus.kundera.examples.pickr.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Entity Class for album
 * 
 * @author amresh.singh
 */

@Entity
@Table(name = "albums", schema = "KunderaExamples@picongo")
public class Album
{
    @Id
    private String albumId;

    @Column(name = "album_name")
    private String albumName;

    @Column(name = "album_desc")
    private String albumDescription;

    // One to many, will be persisted separately
    @OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    private List<Photo> photos;

    public Album()
    {

    }

    public Album(String albumId, String name, String description)
    {
        this.albumId = albumId;
        this.albumName = name;
        this.albumDescription = description;
    }

    public String getAlbumId()
    {
        return albumId;
    }

    public void setAlbumId(String albumId)
    {
        this.albumId = albumId;
    }

    /**
     * @return the albumName
     */
    public String getAlbumName()
    {
        return albumName;
    }

    /**
     * @param albumName
     *            the albumName to set
     */
    public void setAlbumName(String albumName)
    {
        this.albumName = albumName;
    }

    /**
     * @return the albumDescription
     */
    public String getAlbumDescription()
    {
        return albumDescription;
    }

    /**
     * @param albumDescription
     *            the albumDescription to set
     */
    public void setAlbumDescription(String albumDescription)
    {
        this.albumDescription = albumDescription;
    }

    /**
     * @return the photos
     */
    public List<Photo> getPhotos()
    {
        if(photos == null) {
            photos = new ArrayList<Photo>();
        }
        return photos;
    }

    /**
     * @param photos
     *            the photos to set
     */
    public void setPhotos(List<Photo> photos)
    {
        this.photos = photos;
    }
    
    public void addPhoto(Photo photo) {
        getPhotos().add(photo);
    }

}
