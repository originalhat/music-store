#!/usr/bin/env ruby

require 'fileutils'

CONFIG_DIR = "/Users/DGB/Dropbox/school/fall-12/databases/assignments/music-store/config/out"

def populate_artists count

  populate_artists_path = File.join( CONFIG_DIR, "populate-artists.sql" )

  # remove old sql query
  FileUtils.rm populate_artists_path

  # generate new generation scripts
  (0..count).each do | unique_index |
    File.open( populate_artists_path, "a" ) do |f|
       f.puts make_artist unique_index
    end
  end
end

# return a customizable string for populating the 'ARTISTS' table
def make_artist unique_index
  artist_name = (0...8).map{ 65.+(rand(26)).chr }.join
  return "INSERT INTO artists VALUES (#{rand(500)}, '#{artist_name}');"
end

# populate the contacts table, then map the respective contact keys to: employees, vendors,
# vendor contacts, and customers
def populate_contacts count

  contact_keys = []

  populate_contact_path = File.join( CONFIG_DIR, "populate-contact.sql" )

  FileUtils.rm populate_contact_path if File.exists? populate_contact_path

  (1..count).each do | unique_index |
    File.open( populate_contact_path, "a" ) do |f|
      f.puts make_contact unique_index - 1
      contact_keys << unique_index
    end
  end

  populate_contact_path = File.join( CONFIG_DIR, "populate-entites.sql" )

  # remove old sql query
  FileUtils.rm populate_contact_path

  File.open( populate_contact_path, "a" ) do |f|
    contact_keys.each_with_index do | contact_key, index |
      f.puts make_employee index, contact_key - 1 if index < 50

      if (index > 49 and index < 100)
        f.puts make_vendor index - 50, contact_key - 1, ( contact_key + 49 )
      end

      f.puts make_customer index - 150, contact_key - 1 if ( index > 149 and index < 300 )

    end
  end
end

# list of vendors
def vendors
  ['Sony', 'Virgin Records', 'BradDisks', 'Devin\'\'s MP3 Co.']
end

# create a single INSERT command for a contact
def make_contact unique_index

  name    = (0...8).map{ 65.+(rand(26)).chr }.join
  address = "#{rand(10000)} #{name}"
  city    = (0...5).map{ 65.+(rand(26)).chr }.join
  state   = (0...2).map{ 65.+(rand(26)).chr }.join
  email   = "#{name}@#{city}.com"
  phone   = "#{rand(999999)}"

  return %{INSERT INTO contact
    VALUES (
      #{unique_index},
      '#{name}',
      '#{address} Street',
      '#{city}',
      '#{state}',
      #{rand(90000) + 9999},
      '#{email}',
      #{phone});
    }
end

# create a single INSERT command for a employee
def make_employee unique_index, contact_key

  job_title = ['manager', 'sales clerk', 'CEO', 'admin'].shuffle.first
  active    = ['T', 'F'].shuffle.first

  %{INSERT INTO employees VALUES (#{unique_index}, '#{job_title}', '#{active}', #{contact_key});}
end

# create a single INSERT command for a vendor
def make_vendor index, rep_key, company_key
  %{INSERT INTO vendors VALUES (#{index}, #{rep_key}, #{company_key});}
end

# create a single INSERT command for a customer
def make_customer index, contact_key
  promotions = ['T', 'F'].shuffle.first
  %{INSERT INTO customers VALUES (#{index}, '#{promotions}', #{contact_key});}
end

def populate_job_titles

  # prep file / directory
  populate_job_title_path = File.join( CONFIG_DIR, "populate-job-title.sql" )

  FileUtils.rm populate_job_title_path if File.exists? populate_job_title_path

  job_titles  = ['manager', 'sales clerk', 'CEO', 'admin']
  wage_types  = ['salary', 'hourly', 'salary', 'salary']
  wage_amount = [44000.00, 15.00, 120000.00, 250000.00]

  File.open( populate_job_title_path, "a" ) do |f|
    job_titles.each_with_index do | job_title, index |
      f.puts "INSERT INTO job_title VALUES ('#{job_title}', #{wage_amount[index]}, '#{wage_types[index]}');"
    end
  end
end

def populate_music count

  # prep file / directory
  populate_music_path = File.join( CONFIG_DIR, "populate-music.sql" )

  FileUtils.rm populate_music_path if File.exists? populate_music_path

  title = (0...8).map{ 65.+(rand(26)).chr }.join

  File.open( populate_music_path, "a" ) do |f|
    (1..count).each do | index |

      # increment to the next letter, faster and more unique than total random
      title = title.next

        f.puts make_music index, title
    end
  end
end

# create a single INSERT command for a music item
def make_music isbn, title

  music_type = [ 'CD', 'SHEET', 'MP3' ].shuffle.first

  %{INSERT INTO music VALUES (
      '#{title}',
      #{isbn},
      '#{music_type}',
      '#{vendors.shuffle.first}',
      '#{genre.shuffle.first}',
      #{rand(23) + 1990},
      #{rand(99) + 50},
      #{rand(50.00) + 5.00},
      #{rand(100)});
   }
end

def genre
  ['pop', 'rock', 'techno', 'jazz', 'metal', 'folk', 'classical']
end

def populate_promotions count

  # prep file / directory
  populate_promotion_path = File.join( CONFIG_DIR, "populate_promotion.sql" )

  FileUtils.rm populate_promotion_path if File.exists? populate_promotion_path

  (1..count).each do | promotion |
    File.open( populate_promotion_path, "a" ) do |f|

      discount    = rand(100)
      random_isbn = rand(5000)
      random_date = Time.at(0.0 + rand * (Time.now.to_f - 0.0.to_f) + 500000000)

      f.puts "INSERT INTO promotions VALUES (
        #{promotion},
        to_date('#{random_date.strftime("%Y/%m/%d")}','YYYY/MM/DD'),
        to_date('#{(random_date + rand(10000) + 100000).strftime("%Y/%m/%d")}','YYYY/MM/DD'),
        #{discount},
        #{random_isbn});"
    end
  end
end

# begin
  populate_artists 100
  populate_contacts 300
  populate_job_titles
  populate_music 5000
  populate_promotions 100

  puts "Tables populated sucessfully!"
# rescue => e
#   puts "ERROR: #{e}"
# end