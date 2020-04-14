import ChopperMAN as ch
from os import path
import pygame

class Galho(pygame.sprite.Sprite):
    def __init__(self, x, y):
        pygame.sprite.Sprite.__init__(self)
        self.image = pygame.image.load(path.join(ch.img_dir,'Galho.png')).convert_alpha()
        self.image.set_colorkey(ch.BLACK)
        
        self.rect = self.image.get_rect()
        self.rect.x = x
        self.rect.y = y
        
    def update(self):
        if self.rect.x == 200:
            self.image = pygame.image.load(path.join(ch.img_dir,'GalhoE.png')).convert_alpha()
            self.image.set_colorkey(ch.BLACK)
        else:
            self.image = pygame.image.load(path.join(ch.img_dir,'GalhoD.png')).convert_alpha()
            self.image.set_colorkey(ch.BLACK)